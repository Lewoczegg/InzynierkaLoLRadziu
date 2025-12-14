package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.SubmissionEntityMySQL;

import java.util.Optional;

@Repository
public interface SubmissionRepositoryMySQL extends JpaRepository<SubmissionEntityMySQL, Long> {
    Optional<SubmissionEntityMySQL> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId);
    
    @Query("SELECT COUNT(DISTINCT s.assignment.assignmentId) FROM SubmissionEntityMySQL s WHERE s.user.userId = :userId")
    long countDistinctAssignmentsByUserUserId(Long userId);
}
