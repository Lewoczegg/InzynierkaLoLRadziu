package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class DailyTaskResultDomain {

    private Long dailyTaskResultId;

    private UserDomain user;

    private DailyTaskDomain dailyTask;

    private LocalDateTime completedAt;

    private Long points;

    public DailyTaskResultDomain() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public DailyTaskResultDomain(UserDomain user, DailyTaskDomain dailyTask, Long points) {
        this.user = user;
        this.dailyTask = dailyTask;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
