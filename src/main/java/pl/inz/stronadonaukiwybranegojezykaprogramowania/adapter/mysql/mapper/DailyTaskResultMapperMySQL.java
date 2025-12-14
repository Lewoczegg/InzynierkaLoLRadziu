package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskResultEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;

@Component
public class DailyTaskResultMapperMySQL {

    private final UserMapperMySQL userMapper;
    private final DailyTaskMapperMySQL dailyTaskMapper;

    public DailyTaskResultMapperMySQL(UserMapperMySQL userMapper, DailyTaskMapperMySQL dailyTaskMapper) {
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    public DailyTaskResultDomain toDomain(DailyTaskResultEntityMySQL entity) {
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

    public DailyTaskResultEntityMySQL toEntity(DailyTaskResultDomain domain) {
        if (domain == null) {
            return null;
        }
        
        DailyTaskResultEntityMySQL entity = new DailyTaskResultEntityMySQL();
        entity.setDailyTaskResultId(domain.getDailyTaskResultId());
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setDailyTask(dailyTaskMapper.toEntity(domain.getDailyTask()));
        entity.setCompletedAt(domain.getCompletedAt());
        entity.setPoints(domain.getPoints());
        return entity;
    }
}
