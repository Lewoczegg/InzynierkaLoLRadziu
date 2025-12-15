package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public class ProgressEmbedded {

    @Field("courseId")
    private Long courseId;

    @Field("lessonId")
    private Long lessonId;

    @Field("completedAt")
    private Instant completedAt;
}
