package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
