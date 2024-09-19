package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.AssignmentService;

import java.io.IOException;

@RestController
@RequestMapping("/Assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/submit")
    public ResponseEntity<CodeExecutionResponse> submitCode(@RequestBody AssignmentRequest submission) {
        String userCode = submission.getCode();
        String taskId = submission.getTaskId();
        CodeExecutionResponse result = assignmentService.codeFromGuest(userCode, taskId);

        return ResponseEntity.ok(result);
    }
}
