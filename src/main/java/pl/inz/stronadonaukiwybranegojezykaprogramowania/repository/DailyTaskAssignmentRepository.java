package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTaskAssignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyTaskAssignmentRepository extends JpaRepository<DailyTaskAssignment, Long> {
    Optional<DailyTaskAssignment> findByUserAndAssignmentDate(User user, LocalDate date);
}
