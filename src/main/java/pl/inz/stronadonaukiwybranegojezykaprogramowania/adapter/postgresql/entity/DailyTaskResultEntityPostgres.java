package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "daily_task_result")
public class DailyTaskResultEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTaskResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityPostgres user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private DailyTaskEntityPostgres dailyTask;

    private LocalDateTime completedAt;

    private Long points;

    public DailyTaskResultEntityPostgres() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public DailyTaskResultEntityPostgres(UserEntityPostgres user, DailyTaskEntityPostgres dailyTask, Long points) {
        this.user = user;
        this.dailyTask = dailyTask;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
