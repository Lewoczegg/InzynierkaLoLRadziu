package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskAssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.DailyTaskAssignmentMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.UserMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.DailyTaskAssignmentRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DailyTaskAssignmentRepositoryAdapterMySQL implements DailyTaskAssignmentRepositoryAdapter {

    private final DailyTaskAssignmentRepositoryMySQL dailyTaskAssignmentRepository;
    private final DailyTaskAssignmentMapperMySQL dailyTaskAssignmentMapper;
    private final UserMapperMySQL userMapper;

    public DailyTaskAssignmentRepositoryAdapterMySQL(
            DailyTaskAssignmentRepositoryMySQL dailyTaskAssignmentRepository,
            DailyTaskAssignmentMapperMySQL dailyTaskAssignmentMapper,
            UserMapperMySQL userMapper) {
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
