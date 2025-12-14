package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

import java.util.List;
import java.util.Optional;

public interface QuestionRepositoryAdapter {
    QuestionDomain save(QuestionDomain question);
    Optional<QuestionDomain> findById(Long id);
    List<QuestionDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
