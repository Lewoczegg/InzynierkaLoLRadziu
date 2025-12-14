package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class SubmissionEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private AssignmentEntityMySQL assignment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityMySQL user;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String content;

    private Float grade;

    @Column(nullable = false)
    private Timestamp submittedAt;

    private Timestamp gradedAt;
}
