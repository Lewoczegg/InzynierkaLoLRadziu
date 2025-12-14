package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class SubmissionEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private AssignmentEntityPostgres assignment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityPostgres user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "double precision")
    private Float grade;

    @Column(nullable = false)
    private Timestamp submittedAt;

    private Timestamp gradedAt;
}
