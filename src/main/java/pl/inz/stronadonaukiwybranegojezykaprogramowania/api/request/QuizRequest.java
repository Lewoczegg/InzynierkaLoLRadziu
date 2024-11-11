package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizRequest {
    private Long quizId;
    private LocalDateTime startTime;
    private List<String> userAnswer;
}
