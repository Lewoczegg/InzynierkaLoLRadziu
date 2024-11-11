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
import java.util.List;
import java.util.Optional;

@Service
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    private static final int MAX_TIME_LIMIT = 5; // Maksymalny czas w minutach dla dodatkowych punktów
    private static final Long BONUS_POINTS = 20L; // Liczba punktów przyznawanych za szybkie ukończenie quizu

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

        // Sprawdzenie, czy użytkownik już ukończył ten quiz
        Optional<QuizResult> existingResult = quizResultRepository.findByUserAndQuiz(user, quiz);
        if (existingResult.isPresent()) {
            throw new IllegalStateException("Ten test został zrobiony");
        }

        // Sprawdzenie poprawności odpowiedzi użytkownika i przyznanie punktów
        long points = 0;
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (i < userAnswers.size() && question.getCorrectAnswer().equals(userAnswers.get(i))) {
                points += 10; // 10 punktów za poprawną odpowiedź
            }
        }

        LocalDateTime completedAt = LocalDateTime.now();

        // Obliczenie czasu trwania quizu
        long duration = Duration.between(startTime, completedAt).toMinutes();

        // Sprawdzenie, czy quiz został ukończony w wyznaczonym czasie
        if (duration <= MAX_TIME_LIMIT) {
            points += BONUS_POINTS;  // Dodanie dodatkowych punktów
        }

        QuizResult quizResult = new QuizResult(user, quiz, points);
        quizResult.setCompletedAt(completedAt);

        return quizResultRepository.save(quizResult);
    }
}

