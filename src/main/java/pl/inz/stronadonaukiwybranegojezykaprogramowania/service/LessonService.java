package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.CourseRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.LessonRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {


    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // Tworzenie nowej lekcji
    public Lesson createLesson(String title, String content, Long courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setContent(content);
        lesson.setCourse(courseOpt.get());  // Związanie z kursem
        lesson.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        lesson.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return lessonRepository.save(lesson);
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

        // Zwróć lekcje widoczne na podstawie poziomu użytkownika
        int userLevel = user.getLevel();
        List<Lesson> lessons = lessonRepository.findByCourseCourseId(courseId);
        return lessons.stream().filter(lesson -> lesson.getLessonId() <= userLevel).collect(Collectors.toList());
    }

    // Pobieranie wszystkich lekcji
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    // Pobieranie lekcji po ID
    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }
}