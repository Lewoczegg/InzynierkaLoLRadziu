package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.stream.Collectors;

@Component
public class QuizMapperMySQL {

    private final QuestionMapperMySQL questionMapper;

    public QuizMapperMySQL(@Lazy QuestionMapperMySQL questionMapper) {
        this.questionMapper = questionMapper;
    }

    public QuizDomain toDomain(QuizEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        QuizDomain domain = QuizDomain.builder()
                .quizId(entity.getQuizId())
                .title(entity.getTitle())
                .build();
        
        if (entity.getQuestions() != null) {
            domain.setQuestions(entity.getQuestions().stream()
                    .map(questionMapper::toDomain)
                    .collect(Collectors.toList()));
        }
        
        return domain;
    }

    public QuizEntityMySQL toEntity(QuizDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuizEntityMySQL entity = QuizEntityMySQL.builder()
                .quizId(domain.getQuizId())
                .title(domain.getTitle())
                .build();
        
        if (domain.getQuestions() != null) {
            entity.setQuestions(domain.getQuestions().stream()
                    .map(questionMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        
        return entity;
    }
}
