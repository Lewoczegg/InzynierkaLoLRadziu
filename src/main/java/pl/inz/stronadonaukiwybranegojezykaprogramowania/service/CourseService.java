package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.dto.CourseDTO;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CourseRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepositoryAdapter courseRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;
    public CourseService(CourseRepositoryAdapter courseRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        this.courseRepositoryAdapter = courseRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public CourseDomain createCourse(String title, String description, Title titleLvl) {
        CourseDomain course = new CourseDomain();
        course.setTitle(title);
        course.setDescription(description);
        course.setTitleLvl(titleLvl);
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return courseRepositoryAdapter.save(course);
    }

    public List<CourseDTO> getVisibleCoursesForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        Title userTitle = user.getTitle();
        List<CourseDomain> allCourses = getAllCourses();

        return allCourses.stream()
                .map(course -> {
                    boolean available = userTitle.ordinal() >= course.getTitleLvl().ordinal();
                    return new CourseDTO(course, available);
                })
                .collect(Collectors.toList());
    }

    public List<CourseDomain> getAllCourses() {
        return courseRepositoryAdapter.findAll();
    }

    public Optional<CourseDomain> getCourseById(Long id) {
        return courseRepositoryAdapter.findById(id);
    }
}

