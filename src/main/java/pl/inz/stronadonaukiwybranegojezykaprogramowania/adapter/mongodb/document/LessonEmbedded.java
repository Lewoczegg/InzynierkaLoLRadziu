package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class LessonEmbedded {

    @Field("lessonId")
    private Long lessonId;

    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @Field("lessonNumber")
    private Integer lessonNumber;

    @Field("requiredLevel")
    private Integer requiredLevel;

    @Field("createdAt")
    private Instant createdAt;

    @Field("updatedAt")
    private Instant updatedAt;

    @Field("comments")
    private List<CommentEmbedded> comments;

    @Field("assignments")
    private List<AssignmentEmbedded> assignments;
}
