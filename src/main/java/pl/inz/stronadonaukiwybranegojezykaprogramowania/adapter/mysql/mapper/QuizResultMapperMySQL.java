package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.QuizResultEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;

@Component
public class QuizResultMapperMySQL {

    private final UserMapperMySQL userMapper;
    private final QuizMapperMySQL quizMapper;

    public QuizResultMapperMySQL(UserMapperMySQL userMapper, QuizMapperMySQL quizMapper) {
        this.userMapper = userMapper;
        this.quizMapper = quizMapper;
    }

    public QuizResultDomain toDomain(QuizResultEntityMySQL entity) {
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

    public QuizResultEntityMySQL toEntity(QuizResultDomain domain) {
        if (domain == null) {
            return null;
        }
        
        QuizResultEntityMySQL entity = new QuizResultEntityMySQL();
        entity.setQuizResultId(domain.getQuizResultId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setQuiz(quizMapper.toEntity(domain.getQuiz()));
        entity.setCompletedAt(domain.getCompletedAt());
        entity.setPoints(domain.getPoints());
        return entity;
    }
}
