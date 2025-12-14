package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.sql.Timestamp;

@Getter
@Setter
public class AssignmentDomain {
    
    private Long assignmentId;

    private LessonDomain lesson;

    private String title;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Title titleLvl;
}
