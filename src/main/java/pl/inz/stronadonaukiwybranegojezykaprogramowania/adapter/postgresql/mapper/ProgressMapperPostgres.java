package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.ProgressEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;

@Component
public class ProgressMapperPostgres {

    private final UserMapperPostgres userMapper;
    private final CourseMapperPostgres courseMapper;
    private final LessonMapperPostgres lessonMapper;

    public ProgressMapperPostgres(UserMapperPostgres userMapper, CourseMapperPostgres courseMapper, LessonMapperPostgres lessonMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
    }

    public ProgressDomain toDomain(ProgressEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        ProgressDomain domain = new ProgressDomain();
        domain.setProgressId(entity.getProgressId());
        domain.setUser(userMapper.toDomain(entity.getUser()));
        domain.setCourse(courseMapper.toDomain(entity.getCourse()));
        domain.setLesson(lessonMapper.toDomain(entity.getLesson()));
        domain.setCompletedAt(entity.getCompletedAt());
        return domain;
    }

    public ProgressEntityPostgres toEntity(ProgressDomain domain) {
        if (domain == null) {
            return null;
        }
        
        ProgressEntityPostgres entity = new ProgressEntityPostgres();
        entity.setProgressId(domain.getProgressId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setCourse(courseMapper.toEntity(domain.getCourse()));
        entity.setLesson(lessonMapper.toEntity(domain.getLesson()));
        entity.setCompletedAt(domain.getCompletedAt());
        return entity;
    }
}
