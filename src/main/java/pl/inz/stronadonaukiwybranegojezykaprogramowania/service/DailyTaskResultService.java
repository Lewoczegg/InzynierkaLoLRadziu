package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTask;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTaskResult;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.DailyTaskRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.DailyTaskResultRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DailyTaskResultService {

    private final DailyTaskResultRepository dailyTaskResultRepository;

    private final UserRepository userRepository;

    private final DailyTaskRepository dailyTaskRepository;
    private final AssignmentService assignmentService;

    private static final long MAX_TIME_LIMIT = 30;
    private static final long BONUS_POINTS = 50;

    public DailyTaskResultService(DailyTaskResultRepository dailyTaskResultRepository, UserRepository userRepository, DailyTaskRepository dailyTaskRepository, AssignmentService assignmentService) {
        this.dailyTaskResultRepository = dailyTaskResultRepository;
        this.userRepository = userRepository;
        this.dailyTaskRepository = dailyTaskRepository;
        this.assignmentService = assignmentService;
    }

    public CodeExecutionResponse markTaskAsCompleted(String userCode, String taskId, String language, LocalDateTime startTime) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        DailyTask dailyTask = dailyTaskRepository.findById(Long.valueOf(taskId.replaceAll("\\D+", "")))
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));


        Optional<DailyTaskResult> existingResult = dailyTaskResultRepository.findByUserAndDailyTask(user, dailyTask);
        if (existingResult.isPresent()) {
            System.out.println("This task has already been completed");

            // Stworzenie nowego obiektu CodeExecutionResponse i ustawienie odpowiedniego pola
            CodeExecutionResponse response = new CodeExecutionResponse();
            response.setOutput("Task has already been completed");

            return response;
        }



        long points = 0;
        CodeExecutionResponse grade = assignmentService.codeFromGuest(userCode, taskId, language);
        Pattern pattern = Pattern.compile("All tests passed!");
        Matcher matcher = pattern.matcher(grade.getOutput());

        if (matcher.find()) {
            points += 10;
            LocalDateTime completedAt = LocalDateTime.now();
            long duration = Duration.between(startTime, completedAt).toMinutes();
            System.out.println(startTime);
            System.out.println(completedAt);
            System.out.println(duration);
            if (duration <= MAX_TIME_LIMIT) {
                points += BONUS_POINTS;
                System.out.println(points);
                System.out.println("IN");
            }
            System.out.println(points);
            System.out.println("AFTER");
            DailyTaskResult dailyTaskResult = new DailyTaskResult(user, dailyTask, points);
            dailyTaskResult.setCompletedAt(completedAt);
            dailyTaskResultRepository.save(dailyTaskResult);

        }

        return grade;
    }

    public Map<String, Long> getTotalPointsForAllUsers() {
        List<Object[]> results = dailyTaskResultRepository.findTotalPointsForAllUsers();
        Map<String, Long> userPointsMap = new HashMap<>();
        for (Object[] result : results) {
            String username = (String) result[0];
            Long totalPoints = ((Number) result[1]).longValue();
            userPointsMap.put(username, totalPoints);
        }
        return userPointsMap;
    }
}
