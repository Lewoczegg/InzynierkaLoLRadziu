package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SubmissionDomain {

    private Long submissionId;

    private AssignmentDomain assignment;

    private UserDomain user;

    private String content;

    private Float grade;

    private Timestamp submittedAt;

    private Timestamp gradedAt;
}
