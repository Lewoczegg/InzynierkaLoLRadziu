package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    
    @Column(nullable = false)
    private String content;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "options", columnDefinition = "varchar(255)[]")
    private List<String> options;

    @Column(nullable = false)
    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntityPostgres quiz;
}
