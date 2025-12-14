package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.Getter;
import lombok.Setter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.sql.Timestamp;

@Getter
@Setter
public class CourseDomain {

    private Long courseId;

    private String title;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Title titleLvl;
}
