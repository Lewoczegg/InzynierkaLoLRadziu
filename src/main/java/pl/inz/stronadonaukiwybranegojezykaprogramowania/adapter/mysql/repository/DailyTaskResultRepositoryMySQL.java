package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskResultEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.UserEntityMySQL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTaskResultRepositoryMySQL extends JpaRepository<DailyTaskResultEntityMySQL, Long> {
    Optional<DailyTaskResultEntityMySQL> findByUserAndDailyTask(UserEntityMySQL user, DailyTaskEntityMySQL dailyTask);

    @Query("SELECT dtr.user.username AS username, SUM(dtr.points) AS totalPoints FROM DailyTaskResultEntityMySQL dtr GROUP BY dtr.user.username")
    List<Object[]> findTotalPointsForAllUsers();

    @Query("SELECT dtr FROM DailyTaskResultEntityMySQL dtr WHERE dtr.user = :user AND dtr.completedAt BETWEEN :startOfDay AND :endOfDay")
    Optional<DailyTaskResultEntityMySQL> findByUserAndCompletedAtBetween(
            @Param("user") UserEntityMySQL user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}
