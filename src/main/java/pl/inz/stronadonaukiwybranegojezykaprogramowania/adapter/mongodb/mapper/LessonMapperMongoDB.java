package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.LessonEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.sql.Timestamp;

@Component
public class LessonMapperMongoDB {

    public LessonDomain toDomain(LessonEmbedded embedded, CourseDomain course) {
        if (embedded == null) {
            return null;
        }

        LessonDomain domain = new LessonDomain();
        domain.setLessonId(embedded.getLessonId());
        domain.setCourse(course);
        domain.setTitle(embedded.getTitle());
        domain.setContent(embedded.getContent());
        domain.setLessonNumber(embedded.getLessonNumber());
        domain.setRequiredLevel(embedded.getRequiredLevel());
        
        if (embedded.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(embedded.getCreatedAt()));
        }
        if (embedded.getUpdatedAt() != null) {
            domain.setUpdatedAt(Timestamp.from(embedded.getUpdatedAt()));
        }
        
        return domain;
    }

    public LessonEmbedded toEmbedded(LessonDomain domain) {
        if (domain == null) {
            return null;
        }

        LessonEmbedded embedded = new LessonEmbedded();
        embedded.setLessonId(domain.getLessonId());
        embedded.setTitle(domain.getTitle());
        embedded.setContent(domain.getContent());
        embedded.setLessonNumber(domain.getLessonNumber());
        embedded.setRequiredLevel(domain.getRequiredLevel());
        
        if (domain.getCreatedAt() != null) {
            embedded.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        if (domain.getUpdatedAt() != null) {
            embedded.setUpdatedAt(domain.getUpdatedAt().toInstant());
        }
        
        return embedded;
    }
}
