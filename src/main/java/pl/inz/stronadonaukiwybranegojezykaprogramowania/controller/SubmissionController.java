package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.TaskRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.SubmissionService;

import java.util.Map;

@RestController
@RequestMapping("/Submission")
public class SubmissionController {

    private final SubmissionService submissionService;


    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/latest-submission")
    public Map<String, Object> getLatestSubmission(@RequestBody TaskRequest taskRequest) {
        Long taskId = Long.valueOf(taskRequest.getTaskId());
        return submissionService.findLatestSubmission(taskId);
    }

}
