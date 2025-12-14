package pl.inz.stronadonaukiwybranegojezykaprogramowania.dto;

import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private String titleLvl;
    private boolean available;

    public CourseDTO(CourseDomain course, boolean available) {
        this.courseId = course.getCourseId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.titleLvl = String.valueOf(course.getTitleLvl());
        this.available = available;
    }

}
