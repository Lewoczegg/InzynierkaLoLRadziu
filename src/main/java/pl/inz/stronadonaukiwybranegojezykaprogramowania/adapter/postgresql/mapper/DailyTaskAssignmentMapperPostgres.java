package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.DailyTaskAssignmentEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;

@Component
public class DailyTaskAssignmentMapperPostgres {

    private final UserMapperPostgres userMapper;
    private final DailyTaskMapperPostgres dailyTaskMapper;

    public DailyTaskAssignmentMapperPostgres(UserMapperPostgres userMapper, DailyTaskMapperPostgres dailyTaskMapper) {
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    public DailyTaskAssignmentDomain toDomain(DailyTaskAssignmentEntityPostgres entity) {
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

    public DailyTaskAssignmentEntityPostgres toEntity(DailyTaskAssignmentDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return DailyTaskAssignmentEntityPostgres.builder()
                .id(domain.getId())
                .user(userMapper.toEntity(domain.getUser()))
                .dailyTask(dailyTaskMapper.toEntity(domain.getDailyTask()))
                .assignmentDate(domain.getAssignmentDate())
                .build();
    }
}
