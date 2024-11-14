package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.MessageResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTask;
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
    public ResponseEntity<DailyTask> createDailyTask(@RequestBody DailyTask dailyTask) {
        DailyTask createdTask = dailyTaskService.createDailyTask(dailyTask);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<DailyTask> getDailyTaskById(@PathVariable Long taskId) {
        DailyTask dailyTask = dailyTaskService.getDailyTaskById(taskId);
        return ResponseEntity.ok(dailyTask);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DailyTask>> getAllDailyTasks() {
        List<DailyTask> dailyTasks = dailyTaskService.getAllDailyTasks();
        return ResponseEntity.ok(dailyTasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<DailyTask> updateDailyTask(@PathVariable Long taskId, @RequestBody DailyTask updatedDailyTask) {
        DailyTask updatedTask = dailyTaskService.updateDailyTask(taskId, updatedDailyTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteDailyTask(@PathVariable Long taskId) {
        dailyTaskService.deleteDailyTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @GetMapping("/daily")
    public ResponseEntity<?> getDailyTaskForUser() {
        Optional<DailyTask> dailyTask = dailyTaskService.getDailyTaskForUser();
        if (dailyTask.isPresent()) {
            DailyTask taskToReturn = dailyTask.get();
            return ResponseEntity.ok(taskToReturn);
        } else {
            return ResponseEntity.ok(new MessageResponse("done"));
        }
    }
}

