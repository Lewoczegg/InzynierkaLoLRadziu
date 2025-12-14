package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

@Component
public class DailyTaskMapperMySQL {

    public DailyTaskDomain toDomain(DailyTaskEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        DailyTaskDomain domain = new DailyTaskDomain();
        domain.setTaskId(entity.getTaskId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public DailyTaskEntityMySQL toEntity(DailyTaskDomain domain) {
        if (domain == null) {
            return null;
        }
        
        DailyTaskEntityMySQL entity = new DailyTaskEntityMySQL();
        entity.setTaskId(domain.getTaskId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
