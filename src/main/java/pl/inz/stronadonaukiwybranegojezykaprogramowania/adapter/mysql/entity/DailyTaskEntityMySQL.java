package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "daily_task")
public class DailyTaskEntityMySQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    private String description;
}
