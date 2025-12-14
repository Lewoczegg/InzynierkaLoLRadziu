package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskAssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.DailyTaskAssignmentMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.UserMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.DailyTaskAssignmentRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class DailyTaskAssignmentRepositoryAdapterPostgres implements DailyTaskAssignmentRepositoryAdapter {

    private final DailyTaskAssignmentRepositoryPostgres dailyTaskAssignmentRepository;
    private final DailyTaskAssignmentMapperPostgres dailyTaskAssignmentMapper;
    private final UserMapperPostgres userMapper;

    public DailyTaskAssignmentRepositoryAdapterPostgres(
            DailyTaskAssignmentRepositoryPostgres dailyTaskAssignmentRepository,
            DailyTaskAssignmentMapperPostgres dailyTaskAssignmentMapper,
            UserMapperPostgres userMapper) {
        this.dailyTaskAssignmentRepository = dailyTaskAssignmentRepository;
        this.dailyTaskAssignmentMapper = dailyTaskAssignmentMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<DailyTaskAssignmentDomain> findByUserAndAssignmentDate(UserDomain user, LocalDate date) {
        return dailyTaskAssignmentRepository.findByUserAndAssignmentDate(
                userMapper.toEntity(user), date
        ).map(dailyTaskAssignmentMapper::toDomain);
    }

    @Override
    public DailyTaskAssignmentDomain save(DailyTaskAssignmentDomain dailyTaskAssignment) {
        return dailyTaskAssignmentMapper.toDomain(
                dailyTaskAssignmentRepository.save(dailyTaskAssignmentMapper.toEntity(dailyTaskAssignment))
        );
    }

    @Override
    public Optional<DailyTaskAssignmentDomain> findById(Long id) {
        return dailyTaskAssignmentRepository.findById(id)
                .map(dailyTaskAssignmentMapper::toDomain);
    }

    @Override
    public List<DailyTaskAssignmentDomain> findAll() {
        return dailyTaskAssignmentRepository.findAll().stream()
                .map(dailyTaskAssignmentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        dailyTaskAssignmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dailyTaskAssignmentRepository.existsById(id);
    }

    @Override
    public long count() {
        return dailyTaskAssignmentRepository.count();
    }
}
