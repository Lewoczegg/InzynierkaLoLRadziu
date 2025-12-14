package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryAdapter {
    CommentDomain save(CommentDomain comment);
    Optional<CommentDomain> findById(Long id);
    List<CommentDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
