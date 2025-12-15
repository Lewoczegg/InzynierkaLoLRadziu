package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.DailyTaskDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

@Component
public class DailyTaskMapperMongoDB {

    public DailyTaskDomain toDomain(DailyTaskDocument document) {
        if (document == null) {
            return null;
        }

        DailyTaskDomain domain = new DailyTaskDomain();
        domain.setTaskId(document.getId());
        domain.setTitle(document.getTitle());
        domain.setDescription(document.getDescription());
        
        return domain;
    }

    public DailyTaskDocument toDocument(DailyTaskDomain domain) {
        if (domain == null) {
            return null;
        }

        DailyTaskDocument document = new DailyTaskDocument();
        document.setId(domain.getTaskId());
        document.setTitle(domain.getTitle());
        document.setDescription(domain.getDescription());
        
        return document;
    }
}
