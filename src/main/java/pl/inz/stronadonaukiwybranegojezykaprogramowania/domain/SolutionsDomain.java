package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class SolutionsDomain {
    
    private Long solutionId;

    private AssignmentDomain assignment;

    private String content;

    private String language;

    private Timestamp createdAt;
}
