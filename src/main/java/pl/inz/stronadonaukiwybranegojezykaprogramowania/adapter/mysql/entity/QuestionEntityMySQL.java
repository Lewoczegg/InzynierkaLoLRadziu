package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntityMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    
    @Column(nullable = false)
    private String content;

    @ElementCollection
    @CollectionTable(name = "question_options", 
                     joinColumns = @JoinColumn(name = "question_question_id"))
    @Column(name = "options")
    private List<String> options;

    @Column(nullable = false)
    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntityMySQL quiz;
}
