package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeExecutionResponse {
    private String output;
    private String userOutput;
    private String errorOutput;
    private String buildOutput;
    private String buildErrorOutput;
    private boolean success;
    private String errorMessage;
}
