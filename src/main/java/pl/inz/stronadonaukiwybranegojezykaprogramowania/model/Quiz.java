package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;

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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
        question.setQuiz(this);
    }

    // Metoda pomocnicza do usuwania pytania z quizu
    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setQuiz(null);
    }
}
