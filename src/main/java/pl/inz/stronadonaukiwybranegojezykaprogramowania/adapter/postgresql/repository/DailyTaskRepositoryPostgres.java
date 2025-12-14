package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskEntityPostgres;

import java.util.List;

@Repository
public interface DailyTaskRepositoryPostgres extends JpaRepository<DailyTaskEntityPostgres, Long> {
    @Query("SELECT dt FROM DailyTaskEntityPostgres dt WHERE dt.taskId NOT IN (SELECT dtr.dailyTask.taskId FROM DailyTaskResultEntityPostgres dtr WHERE dtr.user.userId = :userId)")
    List<DailyTaskEntityPostgres> findAllNotCompletedByUser(@Param("userId") Long userId);
}
