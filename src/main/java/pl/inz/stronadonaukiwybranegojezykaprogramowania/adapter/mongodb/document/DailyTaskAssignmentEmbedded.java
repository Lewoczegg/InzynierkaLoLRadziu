package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
public class DailyTaskAssignmentEmbedded {

    @Field("taskId")
    private Long taskId; // SQL ID for correlation

    @Field("assignmentDate")
    private Instant assignmentDate;

    @Field("completedAt")
    private Instant completedAt; // Can be null

    @Field("points")
    private Long points; // Can be null
}
