package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Quiz;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.QuizResult;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    Optional<QuizResult> findByUserAndCompletedAtBetween(User user, LocalDateTime start, LocalDateTime end);
    Optional<QuizResult> findByUserAndQuiz(User user, Quiz quiz);
    boolean existsByUserAndQuiz(User user, Quiz quiz);

    @Query("SELECT qr.user.username AS username, SUM(qr.points) AS totalPoints FROM QuizResult qr GROUP BY qr.user.username")
    List<Object[]> findTotalPointsForAllUsers();
}
