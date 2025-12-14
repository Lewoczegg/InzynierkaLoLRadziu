package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskAssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DailyTaskService {

    private final DailyTaskRepositoryAdapter dailyTaskRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final DailyTaskResultRepositoryAdapter dailyTaskResultRepositoryAdapter;
    private final DailyTaskAssignmentRepositoryAdapter dailyTaskAssignmentRepositoryAdapter;
    public DailyTaskService(DailyTaskRepositoryAdapter dailyTaskRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter, DailyTaskResultRepositoryAdapter dailyTaskResultRepositoryAdapter, DailyTaskAssignmentRepositoryAdapter dailyTaskAssignmentRepositoryAdapter) {
        this.dailyTaskRepositoryAdapter = dailyTaskRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.dailyTaskResultRepositoryAdapter = dailyTaskResultRepositoryAdapter;
        this.dailyTaskAssignmentRepositoryAdapter = dailyTaskAssignmentRepositoryAdapter;
    }

    public DailyTaskDomain createDailyTask(DailyTaskDomain dailyTask) {
        return dailyTaskRepositoryAdapter.save(dailyTask);
    }

    public DailyTaskDomain getDailyTaskById(Long taskId) {
        return dailyTaskRepositoryAdapter.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    public List<DailyTaskDomain> getAllDailyTasks() {
        return dailyTaskRepositoryAdapter.findAll();
    }

    public DailyTaskDomain updateDailyTask(Long taskId, DailyTaskDomain updatedDailyTask) {
        DailyTaskDomain existingTask = dailyTaskRepositoryAdapter.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        existingTask.setTitle(updatedDailyTask.getTitle());
        existingTask.setDescription(updatedDailyTask.getDescription());
        return dailyTaskRepositoryAdapter.save(existingTask);
    }

    public void deleteDailyTask(Long taskId) {
        DailyTaskDomain dailyTask = dailyTaskRepositoryAdapter.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        dailyTaskRepositoryAdapter.deleteById(dailyTask.getTaskId());
    }
    @Transactional
    public synchronized Optional<DailyTaskDomain> getDailyTaskForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        LocalDate today = LocalDate.now();

        boolean hasCompletedToday = dailyTaskResultRepositoryAdapter
                .findByUserAndCompletedAtBetween(user, today.atStartOfDay(), today.plusDays(1).atStartOfDay())
                .isPresent();
        if (hasCompletedToday) {
            return Optional.empty();
        }

        Optional<DailyTaskAssignmentDomain> existingAssignment = dailyTaskAssignmentRepositoryAdapter.findByUserAndAssignmentDate(user, today);
        if (existingAssignment.isPresent()) {
            return Optional.of(existingAssignment.get().getDailyTask());
        }

        List<DailyTaskDomain> availableTasks = dailyTaskRepositoryAdapter.findAllNotCompletedByUser(user.getUserId());

        if (availableTasks.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        DailyTaskDomain randomTask = availableTasks.get(random.nextInt(availableTasks.size()));

        DailyTaskAssignmentDomain newAssignment = new DailyTaskAssignmentDomain();
        newAssignment.setUser(user);
        newAssignment.setDailyTask(randomTask);
        newAssignment.setAssignmentDate(today);
        dailyTaskAssignmentRepositoryAdapter.save(newAssignment);
        return Optional.of(randomTask);
    }
}