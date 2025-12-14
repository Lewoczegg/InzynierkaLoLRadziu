package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.DailyTaskAssignmentEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;

@Component
public class DailyTaskAssignmentMapperMySQL {

    private final UserMapperMySQL userMapper;
    private final DailyTaskMapperMySQL dailyTaskMapper;

    public DailyTaskAssignmentMapperMySQL(UserMapperMySQL userMapper, DailyTaskMapperMySQL dailyTaskMapper) {
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    public DailyTaskAssignmentDomain toDomain(DailyTaskAssignmentEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        return DailyTaskAssignmentDomain.builder()
                .id(entity.getId())
                .user(userMapper.toDomain(entity.getUser()))
                .dailyTask(dailyTaskMapper.toDomain(entity.getDailyTask()))
                .assignmentDate(entity.getAssignmentDate())
                .build();
    }

    public DailyTaskAssignmentEntityMySQL toEntity(DailyTaskAssignmentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return DailyTaskAssignmentEntityMySQL.builder()
                .id(domain.getId())
                .user(userMapper.toEntity(domain.getUser()))
                .dailyTask(dailyTaskMapper.toEntity(domain.getDailyTask()))
                .assignmentDate(domain.getAssignmentDate())
                .build();
    }
}
