package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.CourseService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/Courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course.getTitle(), course.getDescription());
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
}
