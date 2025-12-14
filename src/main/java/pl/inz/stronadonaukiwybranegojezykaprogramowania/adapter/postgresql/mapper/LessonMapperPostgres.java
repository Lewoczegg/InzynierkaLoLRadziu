package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.LessonEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

@Component
public class LessonMapperPostgres {

    private final CourseMapperPostgres courseMapper;

    public LessonMapperPostgres(CourseMapperPostgres courseMapper) {
        this.courseMapper = courseMapper;
    }

    public LessonDomain toDomain(LessonEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        LessonDomain domain = new LessonDomain();
        domain.setLessonId(entity.getLessonId());
        domain.setCourse(courseMapper.toDomain(entity.getCourse()));
        domain.setTitle(entity.getTitle());
        domain.setContent(entity.getContent());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setRequiredLevel(entity.getRequiredLevel());
        domain.setLessonNumber(entity.getLessonNumber());
        return domain;
    }

    public LessonEntityPostgres toEntity(LessonDomain domain) {
        if (domain == null) {
            return null;
        }
        
        LessonEntityPostgres entity = new LessonEntityPostgres();
        entity.setLessonId(domain.getLessonId());
        entity.setCourse(courseMapper.toEntity(domain.getCourse()));
        entity.setTitle(domain.getTitle());
        entity.setContent(domain.getContent());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setRequiredLevel(domain.getRequiredLevel());
        entity.setLessonNumber(domain.getLessonNumber());
        return entity;
    }
}
