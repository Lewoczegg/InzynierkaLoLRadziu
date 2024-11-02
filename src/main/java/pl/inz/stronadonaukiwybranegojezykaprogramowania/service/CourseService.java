package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.CourseRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.LessonRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    public CourseService(CourseRepository courseRepository, UserRepository userRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    public Course createCourse(String title, String description) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return courseRepository.save(course);
    }

    public List<Course> getVisibleCoursesForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Zwróć kursy widoczne na podstawie tytułu użytkownika
        String userTitle = String.valueOf(user.getTitle());
        if (userTitle.equals("BEGINNER")) {
            return courseRepository.findByTitleLvl(Title.BEGINNER);
        } else if (userTitle.equals("INTERMEDIATE")) {
            return courseRepository.findByTitleLvlIn(Arrays.asList(Title.BEGINNER, Title.INTERMEDIATE));
        } else if (userTitle.equals("ADVANCED")) {
            return courseRepository.findAll();
        }

        return Collections.emptyList();
    }
    public List<Lesson> getVisibleLessonsForUser(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        int userLevel = user.getLevel();
        List<Lesson> lessons = lessonRepository.findByCourseCourseId(courseId);
        return lessons.stream().filter(lesson -> lesson.getLessonId() <= userLevel).collect(Collectors.toList());
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
}

