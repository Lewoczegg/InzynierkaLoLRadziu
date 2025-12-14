package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDomain {

    private Long commentId;

    private LessonDomain lesson;

    private UserDomain user;

    private String content;

    private Integer rating;

    private Timestamp createdAt;
}
