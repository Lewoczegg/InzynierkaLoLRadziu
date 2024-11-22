package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.LessonRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.dto.LessonDTO;
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

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/add")
    public Lesson createLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.createLesson(lessonRequest.getTitle(),
                lessonRequest.getContent(),
                lessonRequest.getCourseId(),
                lessonRequest.getLessonNumber(),
                lessonRequest.getRequiredLevel());
    }
    @GetMapping("/visible-lessons")
    public ResponseEntity<List<LessonDTO>> getVisibleLessons() {
        try {
            List<LessonDTO> lessons = lessonService.getVisibleLessonsForUser();
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
