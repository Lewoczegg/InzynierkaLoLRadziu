package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

import java.util.List;
import java.util.Optional;

public interface SolutionsRepositoryAdapter {
    List<SolutionsDomain> findByAssignment_AssignmentId(Long assignmentId);
    SolutionsDomain save(SolutionsDomain solutions);
    Optional<SolutionsDomain> findById(Long id);
    List<SolutionsDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
