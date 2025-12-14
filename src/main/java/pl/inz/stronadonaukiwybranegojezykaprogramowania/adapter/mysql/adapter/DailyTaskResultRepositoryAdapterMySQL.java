package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.DailyTaskMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.DailyTaskResultMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.UserMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.DailyTaskResultRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DailyTaskResultRepositoryAdapterMySQL implements DailyTaskResultRepositoryAdapter {

    private final DailyTaskResultRepositoryMySQL dailyTaskResultRepository;
    private final DailyTaskResultMapperMySQL dailyTaskResultMapper;
    private final UserMapperMySQL userMapper;
    private final DailyTaskMapperMySQL dailyTaskMapper;

    public DailyTaskResultRepositoryAdapterMySQL(
            DailyTaskResultRepositoryMySQL dailyTaskResultRepository,
            DailyTaskResultMapperMySQL dailyTaskResultMapper,
            UserMapperMySQL userMapper,
            DailyTaskMapperMySQL dailyTaskMapper) {
        this.dailyTaskResultRepository = dailyTaskResultRepository;
        this.dailyTaskResultMapper = dailyTaskResultMapper;
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndDailyTask(UserDomain user, DailyTaskDomain dailyTask) {
        return dailyTaskResultRepository.findByUserAndDailyTask(
                userMapper.toEntity(user),
                dailyTaskMapper.toEntity(dailyTask)
        ).map(dailyTaskResultMapper::toDomain);
    }

    @Override
    public List<Object[]> findTotalPointsForAllUsers() {
        return dailyTaskResultRepository.findTotalPointsForAllUsers();
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        return dailyTaskResultRepository.findByUserAndCompletedAtBetween(
                userMapper.toEntity(user),
                startOfDay,
                endOfDay
        ).map(dailyTaskResultMapper::toDomain);
    }

    @Override
    public DailyTaskResultDomain save(DailyTaskResultDomain dailyTaskResult) {
        return dailyTaskResultMapper.toDomain(
                dailyTaskResultRepository.save(dailyTaskResultMapper.toEntity(dailyTaskResult))
        );
    }

    @Override
    public Optional<DailyTaskResultDomain> findById(Long id) {
        return dailyTaskResultRepository.findById(id)
                .map(dailyTaskResultMapper::toDomain);
    }

    @Override
    public List<DailyTaskResultDomain> findAll() {
        return dailyTaskResultRepository.findAll().stream()
                .map(dailyTaskResultMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        dailyTaskResultRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dailyTaskResultRepository.existsById(id);
    }

    @Override
    public long count() {
        return dailyTaskResultRepository.count();
    }
}
