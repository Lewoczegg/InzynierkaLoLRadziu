package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizResultEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.UserEntityPostgres;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizResultRepositoryPostgres extends JpaRepository<QuizResultEntityPostgres, Long> {
    Optional<QuizResultEntityPostgres> findByUserAndCompletedAtBetween(UserEntityPostgres user, LocalDateTime start, LocalDateTime end);
    Optional<QuizResultEntityPostgres> findByUserAndQuiz(UserEntityPostgres user, QuizEntityPostgres quiz);
    boolean existsByUserAndQuiz(UserEntityPostgres user, QuizEntityPostgres quiz);

    @Query("SELECT qr.user.username AS username, SUM(qr.points) AS totalPoints FROM QuizResultEntityPostgres qr GROUP BY qr.user.username")
    List<Object[]> findTotalPointsForAllUsers();
}
