package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.LessonRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.LessonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/add")
    public Lesson createLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.createLesson(lessonRequest.getTitle(),
                lessonRequest.getContent(),
                lessonRequest.getCourseId());
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
