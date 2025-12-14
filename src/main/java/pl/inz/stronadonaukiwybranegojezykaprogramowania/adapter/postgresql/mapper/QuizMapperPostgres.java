package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.stream.Collectors;

@Component
public class QuizMapperPostgres {

    private final QuestionMapperPostgres questionMapper;

    public QuizMapperPostgres(@Lazy QuestionMapperPostgres questionMapper) {
        this.questionMapper = questionMapper;
    }

    public QuizDomain toDomain(QuizEntityPostgres entity) {
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

    public QuizEntityPostgres toEntity(QuizDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuizEntityPostgres entity = QuizEntityPostgres.builder()
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
