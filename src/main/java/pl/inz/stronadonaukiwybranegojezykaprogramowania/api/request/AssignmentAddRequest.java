package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentAddRequest {
    private String title;
    private String content;
    private Long lessonId;
}
