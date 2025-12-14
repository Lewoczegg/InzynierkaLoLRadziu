package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntityMySQL lesson;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityMySQL user;

    @Column(nullable = false)
    private String content;

    private Integer rating;

    @Column(nullable = false)
    private Timestamp createdAt;
}
