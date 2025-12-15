package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
public class QuestionEmbedded {

    @Field("questionId")
    private Long questionId;

    @Field("content")
    private String content;

    @Field("options")
    private List<String> options; // MongoDB stores options as simple string array

    @Field("correctAnswer")
    private String correctAnswer;
}
