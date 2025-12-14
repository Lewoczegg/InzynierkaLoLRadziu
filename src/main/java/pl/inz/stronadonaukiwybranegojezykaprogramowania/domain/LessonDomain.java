package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LessonDomain {

    private Long lessonId;

    private CourseDomain course;

    private String title;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer requiredLevel;

    private Integer lessonNumber;
}
