package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "quiz_result")
public class QuizResultEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityMySQL user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntityMySQL quiz;

    private LocalDateTime completedAt;

    private Long points;

    public QuizResultEntityMySQL() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public QuizResultEntityMySQL(UserEntityMySQL user, QuizEntityMySQL quiz, Long points) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
