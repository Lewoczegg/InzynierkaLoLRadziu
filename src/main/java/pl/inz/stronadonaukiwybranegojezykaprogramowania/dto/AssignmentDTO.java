package pl.inz.stronadonaukiwybranegojezykaprogramowania.dto;

import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long assignmentId;
    private Lesson lesson;
    private String title;
    private String description;
    private String titleLvl;
    private boolean available;

    public AssignmentDTO(Assignment assignment, boolean available) {
        this.assignmentId = assignment.getAssignmentId();
        this.lesson = assignment.getLesson();
        this.title = assignment.getTitle();
        this.description = assignment.getDescription();
        this.titleLvl = String.valueOf(assignment.getTitleLvl());
        this.available = available;
    }
}
