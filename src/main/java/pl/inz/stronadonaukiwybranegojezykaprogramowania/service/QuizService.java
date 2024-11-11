package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Question;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Quiz;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.QuizRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.QuizResultRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, QuizResultRepository quizResultRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.quizResultRepository = quizResultRepository;
        this.userRepository = userRepository;
    }
    public Optional<Quiz> getDailyQuizForUser() {
        // Pobierz bieżącego użytkownika z kontekstu bezpieczeństwa
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Sprawdzenie, czy użytkownik rozwiązał już dzisiejszy quiz
        LocalDate today = LocalDate.now();
        boolean hasCompletedToday = quizResultRepository
                .findByUserAndCompletedAtBetween(user, today.atStartOfDay(), today.plusDays(1).atStartOfDay())
                .isPresent();

        if (hasCompletedToday) {
            return Optional.empty();
        }

        // Pobranie quizów, których użytkownik jeszcze nie rozwiązał
        List<Quiz> availableQuizzes = quizRepository.findAllNotCompletedByUser(user.getUserId());

        if (availableQuizzes.isEmpty()) {
            return Optional.empty();
        }

        // Losowe przypisanie quizu
        Random random = new Random();
        Quiz randomQuiz = availableQuizzes.get(random.nextInt(availableQuizzes.size()));
        return Optional.of(randomQuiz);
    }
    public Quiz createQuiz(Quiz quiz) {
        // Ustawianie quizu dla każdego pytania
        if (quiz.getQuestions() != null) {
            for (Question question : quiz.getQuestions()) {
                question.setQuiz(quiz); // Ustawianie relacji pytania z quizem
            }
        }
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz updateQuiz(Long quizId, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
        existingQuiz.setTitle(updatedQuiz.getTitle());

        // Czyścimy istniejące pytania i dodajemy nowe
        existingQuiz.getQuestions().clear();
        if (updatedQuiz.getQuestions() != null) {
            for (Question question : updatedQuiz.getQuestions()) {
                existingQuiz.addQuestion(question); // Dodawanie pytań do istniejącego quizu
            }
        }
        return quizRepository.save(existingQuiz);
    }

    public void deleteQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
        quizRepository.delete(quiz);
    }
}