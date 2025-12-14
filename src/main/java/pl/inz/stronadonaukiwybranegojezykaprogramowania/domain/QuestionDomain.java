package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDomain {

    private Long questionId;
    
    private String content;

    private List<String> options;

    private String correctAnswer;

    private Long quizId;
}
