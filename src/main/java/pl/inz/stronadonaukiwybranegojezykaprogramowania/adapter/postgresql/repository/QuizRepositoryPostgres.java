package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizEntityPostgres;

import java.util.List;

@Repository
public interface QuizRepositoryPostgres extends JpaRepository<QuizEntityPostgres, Long> {
    @Query("SELECT q FROM QuizEntityPostgres q WHERE q.quizId NOT IN (SELECT r.quiz.quizId FROM QuizResultEntityPostgres r WHERE r.user.userId = :userId)")
    List<QuizEntityPostgres> findAllNotCompletedByUser(@Param("userId") Long userId);
}
