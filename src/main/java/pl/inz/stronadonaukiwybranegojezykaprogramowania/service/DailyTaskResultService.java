package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

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

    private final DailyTaskResultRepositoryAdapter dailyTaskResultRepositoryAdapter;

    private final UserRepositoryAdapter userRepositoryAdapter;

    private final DailyTaskRepositoryAdapter dailyTaskRepositoryAdapter;
    private final AssignmentService assignmentService;

    private static final long MAX_TIME_LIMIT = 15;
    private static final long BONUS_POINTS = 20;

    public DailyTaskResultService(DailyTaskResultRepositoryAdapter dailyTaskResultRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter, DailyTaskRepositoryAdapter dailyTaskRepositoryAdapter, AssignmentService assignmentService) {
        this.dailyTaskResultRepositoryAdapter = dailyTaskResultRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.dailyTaskRepositoryAdapter = dailyTaskRepositoryAdapter;
        this.assignmentService = assignmentService;
    }

    public CodeExecutionResponse markTaskAsCompleted(String userCode, String taskId, String language, LocalDateTime startTime) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        DailyTaskDomain dailyTask = dailyTaskRepositoryAdapter.findById(Long.valueOf(taskId.replaceAll("\\D+", "")))
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));


        Optional<DailyTaskResultDomain> existingResult = dailyTaskResultRepositoryAdapter.findByUserAndDailyTask(user, dailyTask);
        if (existingResult.isPresent()) {
            System.out.println("This task has already been completed");

            CodeExecutionResponse response = new CodeExecutionResponse();
            response.setOutput("Task has already been completed");

            return response;
        }



        long points = 0;
        CodeExecutionResponse grade = assignmentService.codeFromGuest(userCode, taskId, language);
        Pattern pattern = Pattern.compile("All tests passed!");
        Matcher matcher = pattern.matcher(grade.getOutput());

        if (matcher.find()) {
            points += 20;
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
            DailyTaskResultDomain dailyTaskResult = new DailyTaskResultDomain(user, dailyTask, points);
            dailyTaskResult.setCompletedAt(completedAt);
            dailyTaskResultRepositoryAdapter.save(dailyTaskResult);

        }

        return grade;
    }
    public Map<String, Long> getTotalPointsForAllUsers() {
        List<Object[]> results = dailyTaskResultRepositoryAdapter.findTotalPointsForAllUsers();
        Map<String, Long> userPointsMap = new HashMap<>();
        for (Object[] result : results) {
            String username = (String) result[0];
            Long totalPoints = ((Number) result[1]).longValue();
            userPointsMap.put(username, totalPoints);
        }
        return userPointsMap;
    }
}
