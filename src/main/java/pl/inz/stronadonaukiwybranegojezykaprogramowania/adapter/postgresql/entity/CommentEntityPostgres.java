package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntityPostgres lesson;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityPostgres user;

    @Column(nullable = false)
    private String content;

    private Integer rating;

    @Column(nullable = false)
    private Timestamp createdAt;
}
