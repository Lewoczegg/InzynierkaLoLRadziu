package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CommentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.sql.Timestamp;

@Component
public class CommentMapperMongoDB {

    public CommentDomain toDomain(CommentEmbedded embedded, LessonDomain lesson, UserDomain user) {
        if (embedded == null) {
            return null;
        }

        CommentDomain domain = new CommentDomain();
        domain.setCommentId(embedded.getCommentId());
        domain.setLesson(lesson);
        domain.setUser(user);
        domain.setContent(embedded.getContent());
        
        if (embedded.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(embedded.getCreatedAt()));
        }
        
        return domain;
    }

    public CommentEmbedded toEmbedded(CommentDomain domain) {
        if (domain == null) {
            return null;
        }

        CommentEmbedded embedded = new CommentEmbedded();
        embedded.setCommentId(domain.getCommentId());
        embedded.setContent(domain.getContent());
        
        if (domain.getUser() != null) {
            embedded.setUserId(domain.getUser().getUserId());
        }
        
        if (domain.getCreatedAt() != null) {
            embedded.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        
        return embedded;
    }
}
