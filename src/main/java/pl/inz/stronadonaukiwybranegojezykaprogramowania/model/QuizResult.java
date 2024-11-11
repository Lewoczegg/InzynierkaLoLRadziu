package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "quiz_result")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private LocalDateTime completedAt;

    private Long points;

    public QuizResult() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public QuizResult(User user, Quiz quiz, Long points) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}

