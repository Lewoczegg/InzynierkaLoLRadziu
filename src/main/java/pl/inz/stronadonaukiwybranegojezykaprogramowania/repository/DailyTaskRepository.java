package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTask;

import java.util.List;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
    @Query("SELECT dt FROM DailyTask dt WHERE dt.taskId NOT IN (SELECT dtr.dailyTask.taskId FROM DailyTaskResult dtr WHERE dtr.user.userId = :userId)")
    List<DailyTask> findAllNotCompletedByUser(@Param("userId") Long userId);

}
