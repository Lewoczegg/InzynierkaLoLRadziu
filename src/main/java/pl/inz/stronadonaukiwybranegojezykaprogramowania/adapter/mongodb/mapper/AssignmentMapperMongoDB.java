package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.AssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.sql.Timestamp;

@Component
public class AssignmentMapperMongoDB {

    public AssignmentDomain toDomain(AssignmentEmbedded embedded, LessonDomain lesson) {
        if (embedded == null) {
            return null;
        }

        AssignmentDomain domain = new AssignmentDomain();
        domain.setAssignmentId(embedded.getAssignmentId());
        domain.setLesson(lesson);
        domain.setTitle(embedded.getTitle());
        domain.setDescription(embedded.getDescription());
        domain.setTitleLvl(embedded.getTitleLevel());
        
        if (embedded.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(embedded.getCreatedAt()));
        }
        if (embedded.getUpdatedAt() != null) {
            domain.setUpdatedAt(Timestamp.from(embedded.getUpdatedAt()));
        }
        
        return domain;
    }

    public AssignmentEmbedded toEmbedded(AssignmentDomain domain) {
        if (domain == null) {
            return null;
        }

        AssignmentEmbedded embedded = new AssignmentEmbedded();
        embedded.setAssignmentId(domain.getAssignmentId());
        embedded.setTitle(domain.getTitle());
        embedded.setDescription(domain.getDescription());
        embedded.setTitleLevel(domain.getTitleLvl());
        
        if (domain.getCreatedAt() != null) {
            embedded.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        if (domain.getUpdatedAt() != null) {
            embedded.setUpdatedAt(domain.getUpdatedAt().toInstant());
        }
        
        return embedded;
    }
}
