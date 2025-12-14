package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "daily_task_result")
public class DailyTaskResultEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTaskResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntityMySQL user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private DailyTaskEntityMySQL dailyTask;

    private LocalDateTime completedAt;

    private Long points;

    public DailyTaskResultEntityMySQL() {
        this.completedAt = LocalDateTime.now();
        this.points = 0L;
    }

    public DailyTaskResultEntityMySQL(UserEntityMySQL user, DailyTaskEntityMySQL dailyTask, Long points) {
        this.user = user;
        this.dailyTask = dailyTask;
        this.completedAt = LocalDateTime.now();
        this.points = points;
    }
}
