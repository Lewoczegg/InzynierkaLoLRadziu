package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.MessageResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.DailyTaskResultService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.DailyTaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/DailyTask")
public class DailyTaskController {

    private final DailyTaskService dailyTaskService;

    public DailyTaskController(DailyTaskService dailyTaskService) {
        this.dailyTaskService = dailyTaskService;

    }

    @PostMapping("/create")
    public ResponseEntity<DailyTaskDomain> createDailyTask(@RequestBody DailyTaskDomain dailyTask) {
        DailyTaskDomain createdTask = dailyTaskService.createDailyTask(dailyTask);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<DailyTaskDomain> getDailyTaskById(@PathVariable Long taskId) {
        DailyTaskDomain dailyTask = dailyTaskService.getDailyTaskById(taskId);
        return ResponseEntity.ok(dailyTask);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DailyTaskDomain>> getAllDailyTasks() {
        List<DailyTaskDomain> dailyTasks = dailyTaskService.getAllDailyTasks();
        return ResponseEntity.ok(dailyTasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<DailyTaskDomain> updateDailyTask(@PathVariable Long taskId, @RequestBody DailyTaskDomain updatedDailyTask) {
        DailyTaskDomain updatedTask = dailyTaskService.updateDailyTask(taskId, updatedDailyTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteDailyTask(@PathVariable Long taskId) {
        dailyTaskService.deleteDailyTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/daily")
    public ResponseEntity<?> getDailyTaskForUser() {
        Optional<DailyTaskDomain> dailyTask = dailyTaskService.getDailyTaskForUser();
        if (dailyTask.isPresent()) {
            DailyTaskDomain taskToReturn = dailyTask.get();
            return ResponseEntity.ok(taskToReturn);
        } else {
            return ResponseEntity.ok(new MessageResponse("done"));
        }
    }
}

