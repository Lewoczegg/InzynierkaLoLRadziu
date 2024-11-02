package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Solutions;

import java.util.List;

@Repository
public interface SolutionsRepository extends JpaRepository<Solutions, Long> {
    List<Solutions> findByAssignment_AssignmentId(Long assignmentId);
}

