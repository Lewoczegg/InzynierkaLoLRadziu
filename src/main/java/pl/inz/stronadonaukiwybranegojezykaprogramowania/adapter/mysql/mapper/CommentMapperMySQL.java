package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.CommentEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;

@Component
public class CommentMapperMySQL {

    private final LessonMapperMySQL lessonMapper;
    private final UserMapperMySQL userMapper;

    public CommentMapperMySQL(LessonMapperMySQL lessonMapper, UserMapperMySQL userMapper) {
        this.lessonMapper = lessonMapper;
        this.userMapper = userMapper;
    }

    public CommentDomain toDomain(CommentEntityMySQL entity) {
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

    public CommentEntityMySQL toEntity(CommentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        CommentEntityMySQL entity = new CommentEntityMySQL();
        entity.setCommentId(domain.getCommentId());
        entity.setLesson(lessonMapper.toEntity(domain.getLesson()));
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setContent(domain.getContent());
        entity.setRating(domain.getRating());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}
