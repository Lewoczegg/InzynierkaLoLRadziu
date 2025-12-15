package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public class QuizResultEmbedded {

    @Field("quizId")
    private Long quizId;

    @Field("completedAt")
    private Instant completedAt;

    @Field("points")
    private Long points;
}
