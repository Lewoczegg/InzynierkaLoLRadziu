package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public class SubmissionEmbedded {

    @Field("submissionId")
    private Long submissionId;

    @Field("content")
    private String content;

    @Field("grade")
    private Integer grade;

    @Field("gradedAt")
    private Instant gradedAt;

    @Field("submittedAt")
    private Instant submittedAt;

    @Field("userId")
    private Long userId;
}
