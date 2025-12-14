package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

@Data

public class AssignmentResponse {
    private AssignmentDomain assignment;
    private String roles;
    public AssignmentResponse(AssignmentDomain assignment, String roles) {
        this.assignment = assignment;
        this.roles = roles;
    }
}
