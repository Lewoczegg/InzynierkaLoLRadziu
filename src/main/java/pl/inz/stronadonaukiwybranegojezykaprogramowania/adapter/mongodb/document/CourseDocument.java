package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Document(collection = "courses")
public class CourseDocument {

    @Id
    private Long id; // Use Long ID directly (matches SQL)

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("titleLevel")
    private Title titleLevel;

    @Field("createdAt")
    private Instant createdAt;

    @Field("updatedAt")
    private Instant updatedAt;

    @Field("lessons")
    private List<LessonEmbedded> lessons;
}
