package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "progress")
public class ProgressEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityPostgres user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntityPostgres course;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntityPostgres lesson;

    private Timestamp completedAt;
}
