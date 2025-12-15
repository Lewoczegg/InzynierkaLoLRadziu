package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.DailyTaskAssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.ZoneId;

@Component
public class DailyTaskAssignmentMapperMongoDB {

    public DailyTaskAssignmentDomain toDomain(DailyTaskAssignmentEmbedded embedded, UserDomain user, DailyTaskDomain dailyTask) {
        if (embedded == null) {
            return null;
        }

        DailyTaskAssignmentDomain.DailyTaskAssignmentDomainBuilder builder = DailyTaskAssignmentDomain.builder()
                .user(user)
                .dailyTask(dailyTask);
        
        if (embedded.getAssignmentDate() != null) {
            builder.assignmentDate(embedded.getAssignmentDate().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        
        return builder.build();
    }

    public DailyTaskAssignmentEmbedded toEmbedded(DailyTaskAssignmentDomain domain) {
        if (domain == null) {
            return null;
        }

        DailyTaskAssignmentEmbedded embedded = new DailyTaskAssignmentEmbedded();
        
        if (domain.getDailyTask() != null) {
            // Will be set as ObjectId reference when saving
            embedded.setTaskId(null); // MongoDB ObjectId will be resolved from taskIdLegacy
        }
        
        if (domain.getAssignmentDate() != null) {
            embedded.setAssignmentDate(domain.getAssignmentDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        
        return embedded;
    }
}
