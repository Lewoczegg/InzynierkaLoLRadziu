package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTask;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.DailyTaskRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.DailyTaskResultRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserRepository userRepository;
    private final DailyTaskResultRepository dailyTaskResultRepository;

    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserRepository userRepository, DailyTaskResultRepository dailyTaskResultRepository) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userRepository = userRepository;
        this.dailyTaskResultRepository = dailyTaskResultRepository;
    }

    public DailyTask createDailyTask(DailyTask dailyTask) {
        return dailyTaskRepository.save(dailyTask);
    }

    public DailyTask getDailyTaskById(Long taskId) {
        return dailyTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    public List<DailyTask> getAllDailyTasks() {
        return dailyTaskRepository.findAll();
    }

    public DailyTask updateDailyTask(Long taskId, DailyTask updatedDailyTask) {
        DailyTask existingTask = dailyTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        existingTask.setTitle(updatedDailyTask.getTitle());
        existingTask.setDescription(updatedDailyTask.getDescription());
        return dailyTaskRepository.save(existingTask);
    }

    public void deleteDailyTask(Long taskId) {
        DailyTask dailyTask = dailyTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        dailyTaskRepository.delete(dailyTask);
    }

    public Optional<DailyTask> getDailyTaskForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        LocalDate today = LocalDate.now();
        boolean hasCompletedToday = dailyTaskResultRepository
                .findByUserAndCompletedAtBetween(user, today.atStartOfDay(), today.plusDays(1).atStartOfDay())
                .isPresent();

        if (hasCompletedToday) {
            return Optional.empty();
        }

        List<DailyTask> availableTasks = dailyTaskRepository.findAllNotCompletedByUser(user.getUserId());

        if (availableTasks.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        DailyTask randomTask = availableTasks.get(random.nextInt(availableTasks.size()));
        return Optional.of(randomTask);
    }

}