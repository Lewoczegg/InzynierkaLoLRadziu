package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentAddRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.LessonRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.AssignmentService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, CodeExecutionResponse>> submitCode(@RequestBody AssignmentRequest submission) {
        CodeExecutionResponse result = assignmentService.codeFromGuest(
                submission.getCode(),
                submission.getTaskId(),
                submission.getLanguage()
        );
        Map<String, CodeExecutionResponse> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public Assignment createLesson(@RequestBody AssignmentAddRequest assignmentAddRequest) {
        return assignmentService.createAssignment(assignmentAddRequest.getTitle(),
                assignmentAddRequest.getContent(),
                assignmentAddRequest.getLessonId());
    }

    @GetMapping("/all")
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public Optional<Assignment> getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }
}
