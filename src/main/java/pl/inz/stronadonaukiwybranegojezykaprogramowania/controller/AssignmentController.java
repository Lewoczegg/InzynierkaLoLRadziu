package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentAddRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.AssignmentService;

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
        CodeExecutionResponse result = assignmentService.submitCode(
                submission.getCode(),
                submission.getTaskId(),
                submission.getLanguage()
        );

        Map<String, CodeExecutionResponse> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/run")
    public ResponseEntity<Map<String, CodeExecutionResponse>> runCode(@RequestBody AssignmentRequest run) {
        CodeExecutionResponse result = assignmentService.codeFromGuest(
                run.getCode(),
                run.getTaskId(),
                run.getLanguage()
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
    @GetMapping("/all-submissions")
    public ResponseEntity<Map<String, Object>> getAllSubmissions(@RequestHeader("Authorization") String token) {
        try {
            Map<String, Object> submissions = assignmentService.getAllSubmissions();
            return ResponseEntity.ok(submissions);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unexpected error occurred"));
        }
    }
}
