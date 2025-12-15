package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;

import java.sql.Timestamp;

@Component
public class CourseMapperMongoDB {

    public CourseDomain toDomain(CourseDocument document) {
        if (document == null) {
            return null;
        }

        CourseDomain domain = new CourseDomain();
        domain.setCourseId(document.getId());
        domain.setTitle(document.getTitle());
        domain.setDescription(document.getDescription());
        domain.setTitleLvl(document.getTitleLevel());
        
        if (document.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(document.getCreatedAt()));
        }
        if (document.getUpdatedAt() != null) {
            domain.setUpdatedAt(Timestamp.from(document.getUpdatedAt()));
        }
        
        return domain;
    }

    public CourseDocument toDocument(CourseDomain domain) {
        if (domain == null) {
            return null;
        }

        CourseDocument document = new CourseDocument();
        document.setId(domain.getCourseId());
        document.setTitle(domain.getTitle());
        document.setDescription(domain.getDescription());
        document.setTitleLevel(domain.getTitleLvl());
        
        if (domain.getCreatedAt() != null) {
            document.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        if (domain.getUpdatedAt() != null) {
            document.setUpdatedAt(domain.getUpdatedAt().toInstant());
        }
        
        return document;
    }
}
