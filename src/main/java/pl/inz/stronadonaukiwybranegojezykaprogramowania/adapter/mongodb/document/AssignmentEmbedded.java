package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class AssignmentEmbedded {

    @Field("assignmentId")
    private Long assignmentId;

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

    @Field("solutions")
    private List<SolutionEmbedded> solutions;

    @Field("submissions")
    private List<SubmissionEmbedded> submissions;
}
