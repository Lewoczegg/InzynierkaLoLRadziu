package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.TaskCompletionRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.DailyTaskResultService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/DailyTaskResult")
public class DailyTaskResultController {

    private final DailyTaskResultService dailyTaskResultService;

    public DailyTaskResultController(DailyTaskResultService dailyTaskResultService) {
        this.dailyTaskResultService = dailyTaskResultService;
    }

    @PostMapping("/complete")
    public ResponseEntity<Map<String, CodeExecutionResponse>> markTaskAsCompleted(@RequestBody TaskCompletionRequest request) {
        CodeExecutionResponse result = dailyTaskResultService.markTaskAsCompleted(
                request.getUserCode(),
                request.getTaskId(),
                request.getLanguage(),
                request.getStartTime()
        );
        Map<String, CodeExecutionResponse> response = new HashMap<>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-points")
    public ResponseEntity<Map<String, Long>> getTotalPointsForAllUsers() {
        Map<String, Long> userPointsMap = dailyTaskResultService.getTotalPointsForAllUsers();
        return ResponseEntity.ok(userPointsMap);
    }
}