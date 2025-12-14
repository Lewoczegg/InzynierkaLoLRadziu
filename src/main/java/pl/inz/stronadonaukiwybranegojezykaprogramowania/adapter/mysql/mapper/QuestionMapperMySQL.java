package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuestionEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

@Component
public class QuestionMapperMySQL {

    private final QuizMapperMySQL quizMapper;

    public QuestionMapperMySQL(QuizMapperMySQL quizMapper) {
        this.quizMapper = quizMapper;
    }

    public QuestionDomain toDomain(QuestionEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        return QuestionDomain.builder()
                .questionId(entity.getQuestionId())
                .content(entity.getContent())
                .options(entity.getOptions())
                .correctAnswer(entity.getCorrectAnswer())
                .quiz(quizMapper.toDomain(entity.getQuiz()))
                .build();
    }

    public QuestionEntityMySQL toEntity(QuestionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return QuestionEntityMySQL.builder()
                .questionId(domain.getQuestionId())
                .content(domain.getContent())
                .options(domain.getOptions())
                .correctAnswer(domain.getCorrectAnswer())
                .quiz(quizMapper.toEntity(domain.getQuiz()))
                .build();
    }
}
