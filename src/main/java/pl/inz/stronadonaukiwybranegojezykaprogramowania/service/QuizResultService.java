package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizResultService {

    private final QuizResultRepositoryAdapter quizResultRepositoryAdapter;
    private final QuizRepositoryAdapter quizRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;

    private static final int MAX_TIME_LIMIT = 5;
    private static final Long BONUS_POINTS = 10L;

    public QuizResultService(QuizResultRepositoryAdapter quizResultRepositoryAdapter, QuizRepositoryAdapter quizRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        this.quizResultRepositoryAdapter = quizResultRepositoryAdapter;
        this.quizRepositoryAdapter = quizRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public QuizResultDomain markQuizAsCompleted(Long quizId, LocalDateTime startTime, List<String> userAnswers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }
        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        QuizDomain quiz = quizRepositoryAdapter.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));

        Optional<QuizResultDomain> existingResult = quizResultRepositoryAdapter.findByUserAndQuiz(user, quiz);
        if (existingResult.isPresent()) {
            throw new IllegalStateException("Ten test został zrobiony");
        }

        long points = 0;
        List<QuestionDomain> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            QuestionDomain question = questions.get(i);
            if (i < userAnswers.size() && question.getCorrectAnswer().equals(userAnswers.get(i))) {
                points += 5;
            }
        }

        LocalDateTime completedAt = LocalDateTime.now();

        long duration = Duration.between(startTime, completedAt).toMinutes();

        if (duration <= MAX_TIME_LIMIT) {
            points += BONUS_POINTS;
        }

        QuizResultDomain quizResult = new QuizResultDomain(user, quiz, points);
        quizResult.setCompletedAt(completedAt);

        return quizResultRepositoryAdapter.save(quizResult);
    }
    public Map<String, Long> getTotalPointsForAllUsers() {
        List<Object[]> results = quizResultRepositoryAdapter.findTotalPointsForAllUsers();
        Map<String, Long> userPointsMap = new HashMap<>();

        for (Object[] result : results) {
            String username = (String) result[0];
            Long totalPoints = ((Number) result[1]).longValue();
            userPointsMap.put(username, totalPoints);
        }

        return userPointsMap;
    }

}

