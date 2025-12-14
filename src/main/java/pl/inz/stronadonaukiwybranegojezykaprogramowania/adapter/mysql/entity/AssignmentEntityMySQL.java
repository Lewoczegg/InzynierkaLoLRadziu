package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "assignments")
public class AssignmentEntityMySQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntityMySQL lesson;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Lob
    private String description;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title titleLvl;
}
