package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;

@Data

public class AssignmentResponse {
    private Assignment assignment;
    private String roles;
    public AssignmentResponse(Assignment assignment, String roles) {
        this.assignment = assignment;
        this.roles = roles;
    }
}
