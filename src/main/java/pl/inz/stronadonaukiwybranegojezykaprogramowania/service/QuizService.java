package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuestionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    private final QuizRepositoryAdapter quizRepositoryAdapter;
    private final QuestionRepositoryAdapter questionRepositoryAdapter;
    private final QuizResultRepositoryAdapter quizResultRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;

    public QuizService(QuizRepositoryAdapter quizRepositoryAdapter, QuestionRepositoryAdapter questionRepositoryAdapter, QuizResultRepositoryAdapter quizResultRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        this.quizRepositoryAdapter = quizRepositoryAdapter;
        this.questionRepositoryAdapter = questionRepositoryAdapter;
        this.quizResultRepositoryAdapter = quizResultRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }
    public Optional<QuizDomain> getDailyQuizForUser() {
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
        boolean hasCompletedToday = quizResultRepositoryAdapter
                .findByUserAndCompletedAtBetween(user, today.atStartOfDay(), today.plusDays(1).atStartOfDay())
                .isPresent();

        if (hasCompletedToday) {
            return Optional.empty();
        }

        List<QuizDomain> availableQuizzes = quizRepositoryAdapter.findAllNotCompletedByUser(user.getUserId());

        if (availableQuizzes.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        QuizDomain randomQuiz = availableQuizzes.get(random.nextInt(availableQuizzes.size()));
        return Optional.of(randomQuiz);
    }
    public QuizDomain createQuiz(QuizDomain quiz) {
        QuizDomain savedQuiz = quizRepositoryAdapter.save(quiz);
        
        if (quiz.getQuestions() != null) {
            for (QuestionDomain question : quiz.getQuestions()) {
                question.setQuizId(savedQuiz.getQuizId());
                questionRepositoryAdapter.save(question);
            }
            savedQuiz.setQuestions(quiz.getQuestions());
        }
        
        return savedQuiz;
    }

    public QuizDomain getQuizById(Long quizId) {
        return quizRepositoryAdapter.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
    }

    public List<QuizDomain> getAllQuizzes() {
        return quizRepositoryAdapter.findAll();
    }

    public QuizDomain updateQuiz(Long quizId, QuizDomain updatedQuiz) {
        QuizDomain existingQuiz = quizRepositoryAdapter.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
        existingQuiz.setTitle(updatedQuiz.getTitle());

        existingQuiz.getQuestions().clear();
        if (updatedQuiz.getQuestions() != null) {
            for (QuestionDomain question : updatedQuiz.getQuestions()) {
                existingQuiz.addQuestion(question);
            }
        }
        return quizRepositoryAdapter.save(existingQuiz);
    }

    public void deleteQuiz(Long quizId) {
        QuizDomain quiz = quizRepositoryAdapter.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
        quizRepositoryAdapter.deleteById(quiz.getQuizId());
    }
}