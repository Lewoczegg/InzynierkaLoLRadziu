package pl.inz.stronadonaukiwybranegojezykaprogramowania.dto;

import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private Long lessonId;
    private Course course;
    private String title;
    private String content;
    private int requiredLevel;
    private boolean available;

    public LessonDTO(Lesson lesson, boolean available) {
        this.lessonId = lesson.getLessonId();
        this.course = lesson.getCourse();
        this.title = lesson.getTitle();
        this.content = lesson.getContent();
        this.requiredLevel = lesson.getRequiredLevel();
        this.available = available;
    }
}
