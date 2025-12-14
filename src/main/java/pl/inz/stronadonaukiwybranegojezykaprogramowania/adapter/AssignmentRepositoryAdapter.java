package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepositoryAdapter {
    AssignmentDomain findByAssignmentId(Long id);
    List<AssignmentDomain> findByLessonLessonId(Long lessonId);
    AssignmentDomain save(AssignmentDomain assignment);
    Optional<AssignmentDomain> findById(Long id);
    List<AssignmentDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
