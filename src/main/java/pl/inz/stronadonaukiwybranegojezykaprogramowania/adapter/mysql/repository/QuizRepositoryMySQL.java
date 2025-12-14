package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizEntityMySQL;

import java.util.List;

@Repository
public interface QuizRepositoryMySQL extends JpaRepository<QuizEntityMySQL, Long> {
    @Query("SELECT q FROM QuizEntityMySQL q WHERE q.quizId NOT IN (SELECT r.quiz.quizId FROM QuizResultEntityMySQL r WHERE r.user.userId = :userId)")
    List<QuizEntityMySQL> findAllNotCompletedByUser(@Param("userId") Long userId);
}
