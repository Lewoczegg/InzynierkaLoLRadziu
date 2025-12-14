package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepositoryAdapter {
    Optional<SubmissionDomain> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId);
    long countDistinctAssignmentsByUserUserId(Long userId);
    SubmissionDomain save(SubmissionDomain submission);
    Optional<SubmissionDomain> findById(Long id);
    List<SubmissionDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
