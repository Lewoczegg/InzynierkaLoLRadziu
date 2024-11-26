package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "solutions")
public class Solutions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solutionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String content;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Timestamp createdAt;

}
