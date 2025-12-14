package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.CourseEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;

@Component
public class CourseMapperPostgres {

    public CourseDomain toDomain(CourseEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        CourseDomain domain = new CourseDomain();
        domain.setCourseId(entity.getCourseId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setTitleLvl(entity.getTitleLvl());
        return domain;
    }

    public CourseEntityPostgres toEntity(CourseDomain domain) {
        if (domain == null) {
            return null;
        }
        
        CourseEntityPostgres entity = new CourseEntityPostgres();
        entity.setCourseId(domain.getCourseId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setTitleLvl(domain.getTitleLvl());
        return entity;
    }
}
