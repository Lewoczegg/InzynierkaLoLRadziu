package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDomain {

    private Long quizId;

    private String title;

    private List<QuestionDomain> questions = new ArrayList<>();

    public void addQuestion(QuestionDomain question) {
        questions.add(question);
        question.setQuiz(this);
    }

    public void removeQuestion(QuestionDomain question) {
        questions.remove(question);
        question.setQuiz(null);
    }
}
