package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.CourseRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(String title, String description) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
}

