package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskResultEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.UserEntityPostgres;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTaskResultRepositoryPostgres extends JpaRepository<DailyTaskResultEntityPostgres, Long> {
    Optional<DailyTaskResultEntityPostgres> findByUserAndDailyTask(UserEntityPostgres user, DailyTaskEntityPostgres dailyTask);

    @Query("SELECT dtr.user.username AS username, SUM(dtr.points) AS totalPoints FROM DailyTaskResultEntityPostgres dtr GROUP BY dtr.user.username")
    List<Object[]> findTotalPointsForAllUsers();

    @Query("SELECT dtr FROM DailyTaskResultEntityPostgres dtr WHERE dtr.user = :user AND dtr.completedAt BETWEEN :startOfDay AND :endOfDay")
    Optional<DailyTaskResultEntityPostgres> findByUserAndCompletedAtBetween(
            @Param("user") UserEntityPostgres user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}
