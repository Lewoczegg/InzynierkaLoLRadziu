package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

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
public class DailyTaskAssignmentEntityMySQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntityMySQL user;

    @ManyToOne
    private DailyTaskEntityMySQL dailyTask;

    private LocalDate assignmentDate;
}
