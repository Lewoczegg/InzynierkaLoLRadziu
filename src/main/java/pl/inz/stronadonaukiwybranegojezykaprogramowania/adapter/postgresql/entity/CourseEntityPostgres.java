package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "title_lvl")
    private Title titleLvl;
}
