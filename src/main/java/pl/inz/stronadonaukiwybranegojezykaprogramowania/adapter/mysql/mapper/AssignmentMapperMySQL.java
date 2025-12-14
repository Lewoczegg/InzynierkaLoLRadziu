package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.AssignmentEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

@Component
public class AssignmentMapperMySQL {

    private final LessonMapperMySQL lessonMapper;

    public AssignmentMapperMySQL(LessonMapperMySQL lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    public AssignmentDomain toDomain(AssignmentEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        AssignmentDomain domain = new AssignmentDomain();
        domain.setAssignmentId(entity.getAssignmentId());
        domain.setLesson(lessonMapper.toDomain(entity.getLesson()));
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setTitleLvl(entity.getTitleLvl());
        return domain;
    }

    public AssignmentEntityMySQL toEntity(AssignmentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        AssignmentEntityMySQL entity = new AssignmentEntityMySQL();
        entity.setAssignmentId(domain.getAssignmentId());
        entity.setLesson(lessonMapper.toEntity(domain.getLesson()));
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setTitleLvl(domain.getTitleLvl());
        return entity;
    }
}
