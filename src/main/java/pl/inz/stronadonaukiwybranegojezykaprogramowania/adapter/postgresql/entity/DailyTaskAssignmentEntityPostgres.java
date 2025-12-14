package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_task_assignment")
public class DailyTaskAssignmentEntityPostgres {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private UserEntityPostgres user;

    @ManyToOne
    @JoinColumn(name = "daily_task_task_id")
    private DailyTaskEntityPostgres dailyTask;

    private LocalDate assignmentDate;
}
