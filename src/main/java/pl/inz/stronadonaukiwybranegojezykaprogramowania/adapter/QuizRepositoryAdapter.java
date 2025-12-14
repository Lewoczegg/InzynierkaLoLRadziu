package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.List;
import java.util.Optional;

public interface QuizRepositoryAdapter {
    List<QuizDomain> findAllNotCompletedByUser(Long userId);
    QuizDomain save(QuizDomain quiz);
    Optional<QuizDomain> findById(Long id);
    List<QuizDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
