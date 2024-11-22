package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonRequest {
    private String title;
    private String content;
    private Long courseId;
    private Integer requiredLevel;
    private Integer lessonNumber;
}