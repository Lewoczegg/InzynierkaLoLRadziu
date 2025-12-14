package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "quiz_result")
public class QuizResultEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityPostgres user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntityPostgres quiz;

    private LocalDateTime completedAt;

    private Long points;

    public QuizResultEntityPostgres() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public QuizResultEntityPostgres(UserEntityPostgres user, QuizEntityPostgres quiz, Long points) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
