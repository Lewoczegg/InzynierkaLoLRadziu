package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCompletionRequest {

    private String userCode;
    private String taskId;
    private String language;
    private LocalDateTime startTime;
}
