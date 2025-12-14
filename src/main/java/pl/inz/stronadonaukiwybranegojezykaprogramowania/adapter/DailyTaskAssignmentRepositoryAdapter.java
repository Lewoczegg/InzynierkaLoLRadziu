package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyTaskAssignmentRepositoryAdapter {
    Optional<DailyTaskAssignmentDomain> findByUserAndAssignmentDate(UserDomain user, LocalDate date);
    DailyTaskAssignmentDomain save(DailyTaskAssignmentDomain dailyTaskAssignment);
    Optional<DailyTaskAssignmentDomain> findById(Long id);
    List<DailyTaskAssignmentDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
