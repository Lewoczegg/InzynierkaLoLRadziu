package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProgressDomain {

    private Long progressId;

    private UserDomain user;

    private CourseDomain course;

    private LessonDomain lesson;

    private Timestamp completedAt;
}
