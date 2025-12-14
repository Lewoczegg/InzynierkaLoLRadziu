package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyTaskResultRepositoryAdapter {
    Optional<DailyTaskResultDomain> findByUserAndDailyTask(UserDomain user, DailyTaskDomain dailyTask);
    List<Object[]> findTotalPointsForAllUsers();
    Optional<DailyTaskResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime startOfDay, LocalDateTime endOfDay);
    DailyTaskResultDomain save(DailyTaskResultDomain dailyTaskResult);
    Optional<DailyTaskResultDomain> findById(Long id);
    List<DailyTaskResultDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
