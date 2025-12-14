package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "progress")
public class ProgressEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityMySQL user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntityMySQL course;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntityMySQL lesson;

    private Timestamp completedAt;
}
