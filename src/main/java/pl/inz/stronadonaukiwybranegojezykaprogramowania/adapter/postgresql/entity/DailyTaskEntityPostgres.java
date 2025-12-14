package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "daily_task")
public class DailyTaskEntityPostgres {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    private String description;
}
