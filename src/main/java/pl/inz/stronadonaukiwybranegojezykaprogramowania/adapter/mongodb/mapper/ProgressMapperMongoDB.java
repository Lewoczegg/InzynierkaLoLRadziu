package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.ProgressEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.sql.Timestamp;

@Component
public class ProgressMapperMongoDB {

    public ProgressDomain toDomain(ProgressEmbedded embedded, UserDomain user, CourseDomain course, LessonDomain lesson) {
        if (embedded == null) {
            return null;
        }

        ProgressDomain domain = new ProgressDomain();
        domain.setUser(user);
        domain.setCourse(course);
        domain.setLesson(lesson);
        
        if (embedded.getCompletedAt() != null) {
            domain.setCompletedAt(Timestamp.from(embedded.getCompletedAt()));
        }
        
        return domain;
    }

    public ProgressEmbedded toEmbedded(ProgressDomain domain) {
        if (domain == null) {
            return null;
        }

        ProgressEmbedded embedded = new ProgressEmbedded();
        
        if (domain.getCourse() != null) {
            // Will be set as ObjectId reference when saving
            embedded.setCourseId(null); // MongoDB ObjectId will be resolved from courseIdLegacy
        }
        
        if (domain.getLesson() != null) {
            // Will be set as ObjectId reference when saving
            embedded.setLessonId(null); // MongoDB ObjectId will be resolved from lessonIdLegacy
        }
        
        if (domain.getCompletedAt() != null) {
            embedded.setCompletedAt(domain.getCompletedAt().toInstant());
        }
        
        return embedded;
    }
}
