package pl.inz.stronadonaukiwybranegojezykaprogramowania.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;


import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

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
    @Column(nullable = false)
    private Title titleLvl;

}
