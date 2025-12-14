package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.dto.LessonDTO;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CourseRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.LessonRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {
    private final LessonRepositoryAdapter lessonRepositoryAdapter;
    private final CourseRepositoryAdapter courseRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;

    public LessonService(LessonRepositoryAdapter lessonRepositoryAdapter, CourseRepositoryAdapter courseRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        this.lessonRepositoryAdapter = lessonRepositoryAdapter;
        this.courseRepositoryAdapter = courseRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }
    public LessonDomain createLesson(String title, String content, Long courseId, Integer lessonNumber, Integer requiredLevel) {
        Optional<CourseDomain> courseOpt = courseRepositoryAdapter.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        LessonDomain lesson = new LessonDomain();
        lesson.setTitle(title);
        lesson.setContent(content);
        lesson.setCourse(courseOpt.get());
        lesson.setLessonNumber(lessonNumber);
        lesson.setRequiredLevel(requiredLevel);
        lesson.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        lesson.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return lessonRepositoryAdapter.save(lesson);
    }

    public List<LessonDTO> getVisibleLessonsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        int userLevel = user.getLevel();
        List<LessonDomain> allLessons = getAllLessons();
        return allLessons.stream()
                .map(lesson -> {
                    boolean available = userLevel >= lesson.getRequiredLevel();
                    return new LessonDTO(lesson, available);
                })
                .collect(Collectors.toList());
    }

    public List<LessonDomain> getAllLessons() {
        return lessonRepositoryAdapter.findAll();
    }

    public Optional<LessonDomain> getLessonById(Long id) {
        return lessonRepositoryAdapter.findById(id);
    }
}