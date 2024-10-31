package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolutionsAddRequest {
    private String content;
    private String language;
    private Long assignmentId;
}
