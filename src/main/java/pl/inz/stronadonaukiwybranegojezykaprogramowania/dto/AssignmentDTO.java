package pl.inz.stronadonaukiwybranegojezykaprogramowania.dto;

import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long assignmentId;
    private LessonDomain lesson;
    private String title;
    private String description;
    private String titleLvl;
    private boolean available;

    public AssignmentDTO(AssignmentDomain assignment, boolean available) {
        this.assignmentId = assignment.getAssignmentId();
        this.lesson = assignment.getLesson();
        this.title = assignment.getTitle();
        this.description = assignment.getDescription();
        this.titleLvl = String.valueOf(assignment.getTitleLvl());
        this.available = available;
    }
}
