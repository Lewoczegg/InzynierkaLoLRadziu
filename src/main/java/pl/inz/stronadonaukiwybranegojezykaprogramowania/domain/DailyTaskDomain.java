package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

@Getter
@Setter
public class DailyTaskDomain {
    
    private Long taskId;

    private String title;

    private String description;
}
