package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

import java.util.List;
import java.util.Optional;

public interface DailyTaskRepositoryAdapter {
    List<DailyTaskDomain> findAllNotCompletedByUser(Long userId);
    DailyTaskDomain save(DailyTaskDomain dailyTask);
    Optional<DailyTaskDomain> findById(Long id);
    List<DailyTaskDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
