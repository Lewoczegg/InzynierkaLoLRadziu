package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuizResultRepositoryAdapter {
    Optional<QuizResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime start, LocalDateTime end);
    Optional<QuizResultDomain> findByUserAndQuiz(UserDomain user, QuizDomain quiz);
    boolean existsByUserAndQuiz(UserDomain user, QuizDomain quiz);
    List<Object[]> findTotalPointsForAllUsers();
    QuizResultDomain save(QuizResultDomain quizResult);
    Optional<QuizResultDomain> findById(Long id);
    List<QuizResultDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
