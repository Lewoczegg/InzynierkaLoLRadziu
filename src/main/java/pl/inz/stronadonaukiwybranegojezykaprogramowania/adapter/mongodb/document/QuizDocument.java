package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "quizzes")
public class QuizDocument {

    @Id
    private Long id; // Use Long ID directly (matches SQL)

    @Field("title")
    private String title;

    @Field("questions")
    private List<QuestionEmbedded> questions;
}
