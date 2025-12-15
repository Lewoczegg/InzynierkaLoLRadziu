package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public class SolutionEmbedded {

    @Field("solutionId")
    private Long solutionId;

    @Field("content")
    private String content;

    @Field("language")
    private String language; // "java" or "python"

    @Field("createdAt")
    private Instant createdAt;
}
