package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTask;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.DailyTaskResult;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTaskResultRepository extends JpaRepository<DailyTaskResult, Long> {
    Optional<DailyTaskResult> findByUserAndDailyTask(User user, DailyTask dailyTask);

    @Query("SELECT dtr.user.username AS username, SUM(dtr.points) AS totalPoints FROM DailyTaskResult dtr GROUP BY dtr.user.username")
    List<Object[]> findTotalPointsForAllUsers();

    @Query("SELECT dtr FROM DailyTaskResult dtr WHERE dtr.user = :user AND dtr.completedAt BETWEEN :startOfDay AND :endOfDay")
    Optional<DailyTaskResult> findByUserAndCompletedAtBetween(
            @Param("user") User user,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}
