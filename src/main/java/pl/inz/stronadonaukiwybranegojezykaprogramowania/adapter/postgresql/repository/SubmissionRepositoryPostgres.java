package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.SubmissionEntityPostgres;

import java.util.Optional;

@Repository
public interface SubmissionRepositoryPostgres extends JpaRepository<SubmissionEntityPostgres, Long> {
    Optional<SubmissionEntityPostgres> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId);
    
    @Query("SELECT COUNT(DISTINCT s.assignment.assignmentId) FROM SubmissionEntityPostgres s WHERE s.user.userId = :userId")
    long countDistinctAssignmentsByUserUserId(Long userId);
}
