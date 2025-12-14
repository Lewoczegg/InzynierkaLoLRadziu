package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz")
public class QuizEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntityPostgres> questions = new ArrayList<>();

    public void addQuestion(QuestionEntityPostgres question) {
        questions.add(question);
        question.setQuiz(this);
    }

    public void removeQuestion(QuestionEntityPostgres question) {
        questions.remove(question);
        question.setQuiz(null);
    }
}
