package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

@Component
public class DailyTaskMapperPostgres {

    public DailyTaskDomain toDomain(DailyTaskEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        DailyTaskDomain domain = new DailyTaskDomain();
        domain.setTaskId(entity.getTaskId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public DailyTaskEntityPostgres toEntity(DailyTaskDomain domain) {
        if (domain == null) {
            return null;
        }
        
        DailyTaskEntityPostgres entity = new DailyTaskEntityPostgres();
        entity.setTaskId(domain.getTaskId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
