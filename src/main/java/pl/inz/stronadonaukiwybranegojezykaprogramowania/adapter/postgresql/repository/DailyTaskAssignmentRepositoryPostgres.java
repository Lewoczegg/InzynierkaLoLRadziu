package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskAssignmentEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.UserEntityPostgres;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyTaskAssignmentRepositoryPostgres extends JpaRepository<DailyTaskAssignmentEntityPostgres, Long> {
    Optional<DailyTaskAssignmentEntityPostgres> findByUserAndAssignmentDate(UserEntityPostgres user, LocalDate date);
}
