package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuizResultDomain {

    private Long quizResultId;

    private UserDomain user;

    private QuizDomain quiz;

    private LocalDateTime completedAt;

    private Long points;

    public QuizResultDomain() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public QuizResultDomain(UserDomain user, QuizDomain quiz, Long points) {
        this.user = user;
        this.quiz = quiz;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
