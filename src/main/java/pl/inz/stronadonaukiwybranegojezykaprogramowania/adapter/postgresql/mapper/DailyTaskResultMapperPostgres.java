package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskResultEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;

@Component
public class DailyTaskResultMapperPostgres {

    private final UserMapperPostgres userMapper;
    private final DailyTaskMapperPostgres dailyTaskMapper;

    public DailyTaskResultMapperPostgres(UserMapperPostgres userMapper, DailyTaskMapperPostgres dailyTaskMapper) {
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    public DailyTaskResultDomain toDomain(DailyTaskResultEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        DailyTaskResultDomain domain = new DailyTaskResultDomain();
        domain.setDailyTaskResultId(entity.getDailyTaskResultId());
        domain.setUser(userMapper.toDomain(entity.getUser()));
        domain.setDailyTask(dailyTaskMapper.toDomain(entity.getDailyTask()));
        domain.setCompletedAt(entity.getCompletedAt());
        domain.setPoints(entity.getPoints());
        return domain;
    }

    public DailyTaskResultEntityPostgres toEntity(DailyTaskResultDomain domain) {
        if (domain == null) {
            return null;
        }
        
        DailyTaskResultEntityPostgres entity = new DailyTaskResultEntityPostgres();
        entity.setDailyTaskResultId(domain.getDailyTaskResultId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setDailyTask(dailyTaskMapper.toEntity(domain.getDailyTask()));
        entity.setCompletedAt(domain.getCompletedAt());
        entity.setPoints(domain.getPoints());
        return entity;
    }
}
