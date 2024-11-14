package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "daily_task_result")
public class DailyTaskResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTaskResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private DailyTask dailyTask;

    private LocalDateTime completedAt;

    private Long points;

    public DailyTaskResult() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public DailyTaskResult(User user, DailyTask dailyTask, Long points) {
        this.user = user;
        this.dailyTask = dailyTask;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}

