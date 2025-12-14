package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuestionEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

@Component
public class QuestionMapperPostgres {

    private final QuizMapperPostgres quizMapper;

    public QuestionMapperPostgres(QuizMapperPostgres quizMapper) {
        this.quizMapper = quizMapper;
    }

    public QuestionDomain toDomain(QuestionEntityPostgres entity) {
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

    public QuestionEntityPostgres toEntity(QuestionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return QuestionEntityPostgres.builder()
                .questionId(domain.getQuestionId())
                .content(domain.getContent())
                .options(domain.getOptions())
                .correctAnswer(domain.getCorrectAnswer())
                .quiz(quizMapper.toEntity(domain.getQuiz()))
                .build();
    }
}
