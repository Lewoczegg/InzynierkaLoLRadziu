package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.AssignmentEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

@Component
public class AssignmentMapperPostgres {

    private final LessonMapperPostgres lessonMapper;

    public AssignmentMapperPostgres(LessonMapperPostgres lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    public AssignmentDomain toDomain(AssignmentEntityPostgres entity) {
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

    public AssignmentEntityPostgres toEntity(AssignmentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        AssignmentEntityPostgres entity = new AssignmentEntityPostgres();
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
