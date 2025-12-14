package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuizResultEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;

@Component
public class QuizResultMapperPostgres {

    private final UserMapperPostgres userMapper;
    private final QuizMapperPostgres quizMapper;

    public QuizResultMapperPostgres(UserMapperPostgres userMapper, QuizMapperPostgres quizMapper) {
        this.userMapper = userMapper;
        this.quizMapper = quizMapper;
    }

    public QuizResultDomain toDomain(QuizResultEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        QuizResultDomain domain = new QuizResultDomain();
        domain.setQuizResultId(entity.getQuizResultId());
        domain.setUser(userMapper.toDomain(entity.getUser()));
        domain.setQuiz(quizMapper.toDomain(entity.getQuiz()));
        domain.setCompletedAt(entity.getCompletedAt());
        domain.setPoints(entity.getPoints());
        return domain;
    }

    public QuizResultEntityPostgres toEntity(QuizResultDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuizResultEntityPostgres entity = new QuizResultEntityPostgres();
        entity.setQuizResultId(domain.getQuizResultId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setQuiz(quizMapper.toEntity(domain.getQuiz()));
        entity.setCompletedAt(domain.getCompletedAt());
        entity.setPoints(domain.getPoints());
        return entity;
    }
}
