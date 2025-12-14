package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.CommentEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;

@Component
public class CommentMapperPostgres {

    private final LessonMapperPostgres lessonMapper;
    private final UserMapperPostgres userMapper;

    public CommentMapperPostgres(LessonMapperPostgres lessonMapper, UserMapperPostgres userMapper) {
        this.lessonMapper = lessonMapper;
        this.userMapper = userMapper;
    }

    public CommentDomain toDomain(CommentEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        CommentDomain domain = new CommentDomain();
        domain.setCommentId(entity.getCommentId());
        domain.setLesson(lessonMapper.toDomain(entity.getLesson()));
        domain.setUser(userMapper.toDomain(entity.getUser()));
        domain.setContent(entity.getContent());
        domain.setRating(entity.getRating());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public CommentEntityPostgres toEntity(CommentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        CommentEntityPostgres entity = new CommentEntityPostgres();
        entity.setCommentId(domain.getCommentId());
        entity.setLesson(lessonMapper.toEntity(domain.getLesson()));
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setContent(domain.getContent());
        entity.setRating(domain.getRating());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}
