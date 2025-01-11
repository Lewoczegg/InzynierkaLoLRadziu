package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Question;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Quiz;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.QuizResult;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.QuizRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.QuizResultRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    private static final int MAX_TIME_LIMIT = 5;
    private static final Long BONUS_POINTS = 10L;

    public QuizResultService(QuizResultRepository quizResultRepository, QuizRepository quizRepository, UserRepository userRepository) {
        this.quizResultRepository = quizResultRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public QuizResult markQuizAsCompleted(Long quizId, LocalDateTime startTime, List<String> userAnswers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));

        Optional<QuizResult> existingResult = quizResultRepository.findByUserAndQuiz(user, quiz);
        if (existingResult.isPresent()) {
            throw new IllegalStateException("Ten test został zrobiony");
        }

        long points = 0;
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (i < userAnswers.size() && question.getCorrectAnswer().equals(userAnswers.get(i))) {
                points += 5;
            }
        }

        LocalDateTime completedAt = LocalDateTime.now();

        long duration = Duration.between(startTime, completedAt).toMinutes();

        if (duration <= MAX_TIME_LIMIT) {
            points += BONUS_POINTS;
        }

        QuizResult quizResult = new QuizResult(user, quiz, points);
        quizResult.setCompletedAt(completedAt);

        return quizResultRepository.save(quizResult);
    }
    public Map<String, Long> getTotalPointsForAllUsers() {
        List<Object[]> results = quizResultRepository.findTotalPointsForAllUsers();
        Map<String, Long> userPointsMap = new HashMap<>();

        for (Object[] result : results) {
            String username = (String) result[0];
            Long totalPoints = ((Number) result[1]).longValue();
            userPointsMap.put(username, totalPoints);
        }

        return userPointsMap;
    }

}

