package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuestionEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

@Component
public class QuestionMapperPostgres {

    public QuestionDomain toDomain(QuestionEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        return QuestionDomain.builder()
                .questionId(entity.getQuestionId())
                .content(entity.getContent())
                .options(entity.getOptions())
                .correctAnswer(entity.getCorrectAnswer())
                .quizId(entity.getQuiz() != null ? entity.getQuiz().getQuizId() : null)
                .build();
    }

    public QuestionEntityPostgres toEntity(QuestionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuestionEntityPostgres entity = QuestionEntityPostgres.builder()
                .questionId(domain.getQuestionId())
                .content(domain.getContent())
                .options(domain.getOptions())
                .correctAnswer(domain.getCorrectAnswer())
                .build();
        
        if (domain.getQuizId() != null) {
            QuizEntityPostgres quiz = new QuizEntityPostgres();
            quiz.setQuizId(domain.getQuizId());
            entity.setQuiz(quiz);
        }
        
        return entity;
    }
}
