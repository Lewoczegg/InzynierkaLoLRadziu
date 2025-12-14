package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyTaskAssignmentDomain {
    
    private Long id;

    private UserDomain user;

    private DailyTaskDomain dailyTask;

    private LocalDate assignmentDate;
}
