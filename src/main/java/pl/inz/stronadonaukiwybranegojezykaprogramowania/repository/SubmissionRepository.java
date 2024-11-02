package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Submission;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId);

}
