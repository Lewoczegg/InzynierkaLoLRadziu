package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.LessonRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.CourseService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.LessonService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Lessons")
public class LessonController {

    private final LessonService lessonService;
    private final CourseService courseService;

    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public Lesson createLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.createLesson(lessonRequest.getTitle(),
                lessonRequest.getContent(),
                lessonRequest.getCourseId());
    }
    @GetMapping("/visible-lessons")
    public ResponseEntity<List<Lesson>> getVisibleLessons(@RequestParam Long courseId) {
        try {
            List<Lesson> lessons = courseService.getVisibleLessonsForUser(courseId);
            return ResponseEntity.ok(lessons);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
    @GetMapping("/all")
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }
}
