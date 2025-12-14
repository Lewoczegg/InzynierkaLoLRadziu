package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskAssignmentEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.UserEntityMySQL;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyTaskAssignmentRepositoryMySQL extends JpaRepository<DailyTaskAssignmentEntityMySQL, Long> {
    Optional<DailyTaskAssignmentEntityMySQL> findByUserAndAssignmentDate(UserEntityMySQL user, LocalDate date);
}
