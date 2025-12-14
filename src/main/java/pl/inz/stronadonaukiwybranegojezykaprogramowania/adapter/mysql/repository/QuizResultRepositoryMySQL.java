package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizResultEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.UserEntityMySQL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizResultRepositoryMySQL extends JpaRepository<QuizResultEntityMySQL, Long> {
    Optional<QuizResultEntityMySQL> findByUserAndCompletedAtBetween(UserEntityMySQL user, LocalDateTime start, LocalDateTime end);
    Optional<QuizResultEntityMySQL> findByUserAndQuiz(UserEntityMySQL user, QuizEntityMySQL quiz);
    boolean existsByUserAndQuiz(UserEntityMySQL user, QuizEntityMySQL quiz);

    @Query("SELECT qr.user.username AS username, SUM(qr.points) AS totalPoints FROM QuizResultEntityMySQL qr GROUP BY qr.user.username")
    List<Object[]> findTotalPointsForAllUsers();
}
