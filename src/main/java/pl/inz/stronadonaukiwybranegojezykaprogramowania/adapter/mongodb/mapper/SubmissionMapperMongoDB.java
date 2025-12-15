package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SubmissionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.sql.Timestamp;

@Component
public class SubmissionMapperMongoDB {

    public SubmissionDomain toDomain(SubmissionEmbedded embedded, AssignmentDomain assignment, UserDomain user) {
        if (embedded == null) {
            return null;
        }

        SubmissionDomain domain = new SubmissionDomain();
        domain.setSubmissionId(embedded.getSubmissionId());
        domain.setAssignment(assignment);
        domain.setUser(user);
        domain.setContent(embedded.getContent());
        
        if (embedded.getGrade() != null) {
            domain.setGrade(embedded.getGrade().floatValue());
        }
        
        if (embedded.getSubmittedAt() != null) {
            domain.setSubmittedAt(Timestamp.from(embedded.getSubmittedAt()));
        }
        if (embedded.getGradedAt() != null) {
            domain.setGradedAt(Timestamp.from(embedded.getGradedAt()));
        }
        
        return domain;
    }

    public SubmissionEmbedded toEmbedded(SubmissionDomain domain) {
        if (domain == null) {
            return null;
        }

        SubmissionEmbedded embedded = new SubmissionEmbedded();
        embedded.setSubmissionId(domain.getSubmissionId());
        embedded.setContent(domain.getContent());
        
        if (domain.getGrade() != null) {
            embedded.setGrade(domain.getGrade().intValue());
        }
        
        if (domain.getUser() != null) {
            embedded.setUserId(domain.getUser().getUserId());
        }
        
        if (domain.getSubmittedAt() != null) {
            embedded.setSubmittedAt(domain.getSubmittedAt().toInstant());
        }
        if (domain.getGradedAt() != null) {
            embedded.setGradedAt(domain.getGradedAt().toInstant());
        }
        
        return embedded;
    }
}
