package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "daily_task")
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    private String description;
}

