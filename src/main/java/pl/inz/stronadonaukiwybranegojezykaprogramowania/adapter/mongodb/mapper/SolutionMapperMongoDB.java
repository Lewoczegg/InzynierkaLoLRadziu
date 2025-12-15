package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SolutionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

import java.sql.Timestamp;

@Component
public class SolutionMapperMongoDB {

    public SolutionsDomain toDomain(SolutionEmbedded embedded, AssignmentDomain assignment) {
        if (embedded == null) {
            return null;
        }

        SolutionsDomain domain = new SolutionsDomain();
        domain.setSolutionId(embedded.getSolutionId());
        domain.setAssignment(assignment);
        domain.setContent(embedded.getContent());
        domain.setLanguage(embedded.getLanguage());
        
        if (embedded.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(embedded.getCreatedAt()));
        }
        
        return domain;
    }

    public SolutionEmbedded toEmbedded(SolutionsDomain domain) {
        if (domain == null) {
            return null;
        }

        SolutionEmbedded embedded = new SolutionEmbedded();
        embedded.setSolutionId(domain.getSolutionId());
        embedded.setContent(domain.getContent());
        embedded.setLanguage(domain.getLanguage());
        
        if (domain.getCreatedAt() != null) {
            embedded.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        
        return embedded;
    }
}
