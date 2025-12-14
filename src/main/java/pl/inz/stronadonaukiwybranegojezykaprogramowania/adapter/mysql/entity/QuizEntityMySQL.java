package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

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
public class QuizEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntityMySQL> questions = new ArrayList<>();

    public void addQuestion(QuestionEntityMySQL question) {
        questions.add(question);
        question.setQuiz(this);
    }

    public void removeQuestion(QuestionEntityMySQL question) {
        questions.remove(question);
        question.setQuiz(null);
    }
}
