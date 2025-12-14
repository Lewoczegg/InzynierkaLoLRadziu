package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.ProgressEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;

@Component
public class ProgressMapperMySQL {

    private final UserMapperMySQL userMapper;
    private final CourseMapperMySQL courseMapper;
    private final LessonMapperMySQL lessonMapper;

    public ProgressMapperMySQL(UserMapperMySQL userMapper, CourseMapperMySQL courseMapper, LessonMapperMySQL lessonMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
    }

    public ProgressDomain toDomain(ProgressEntityMySQL entity) {
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

    public ProgressEntityMySQL toEntity(ProgressDomain domain) {
        if (domain == null) {
            return null;
        }
        
        ProgressEntityMySQL entity = new ProgressEntityMySQL();
        entity.setProgressId(domain.getProgressId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setCourse(courseMapper.toEntity(domain.getCourse()));
        entity.setLesson(lessonMapper.toEntity(domain.getLesson()));
        entity.setCompletedAt(domain.getCompletedAt());
        return entity;
    }
}
