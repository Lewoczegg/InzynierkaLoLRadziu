package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskEntityMySQL;

import java.util.List;

@Repository
public interface DailyTaskRepositoryMySQL extends JpaRepository<DailyTaskEntityMySQL, Long> {
    @Query("SELECT dt FROM DailyTaskEntityMySQL dt WHERE dt.taskId NOT IN (SELECT dtr.dailyTask.taskId FROM DailyTaskResultEntityMySQL dtr WHERE dtr.user.userId = :userId)")
    List<DailyTaskEntityMySQL> findAllNotCompletedByUser(@Param("userId") Long userId);
}
