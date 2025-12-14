package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuestionEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

@Component
public class QuestionMapperMySQL {

    public QuestionDomain toDomain(QuestionEntityMySQL entity) {
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

    public QuestionEntityMySQL toEntity(QuestionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuestionEntityMySQL entity = QuestionEntityMySQL.builder()
                .questionId(domain.getQuestionId())
                .content(domain.getContent())
                .options(domain.getOptions())
                .correctAnswer(domain.getCorrectAnswer())
                .build();
        
        if (domain.getQuizId() != null) {
            QuizEntityMySQL quiz = new QuizEntityMySQL();
            quiz.setQuizId(domain.getQuizId());
            entity.setQuiz(quiz);
        }
        
        return entity;
    }
}
