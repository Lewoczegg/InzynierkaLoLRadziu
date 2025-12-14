package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.DailyTaskMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.DailyTaskRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DailyTaskRepositoryAdapterPostgres implements DailyTaskRepositoryAdapter {

    private final DailyTaskRepositoryPostgres dailyTaskRepository;
    private final DailyTaskMapperPostgres dailyTaskMapper;

    public DailyTaskRepositoryAdapterPostgres(DailyTaskRepositoryPostgres dailyTaskRepository, DailyTaskMapperPostgres dailyTaskMapper) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.dailyTaskMapper = dailyTaskMapper;
    }

    @Override
    public List<DailyTaskDomain> findAllNotCompletedByUser(Long userId) {
        return dailyTaskRepository.findAllNotCompletedByUser(userId).stream()
                .map(dailyTaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public DailyTaskDomain save(DailyTaskDomain dailyTask) {
        return dailyTaskMapper.toDomain(
                dailyTaskRepository.save(dailyTaskMapper.toEntity(dailyTask))
        );
    }

    @Override
    public Optional<DailyTaskDomain> findById(Long id) {
        return dailyTaskRepository.findById(id)
                .map(dailyTaskMapper::toDomain);
    }

    @Override
    public List<DailyTaskDomain> findAll() {
        return dailyTaskRepository.findAll().stream()
                .map(dailyTaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        dailyTaskRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dailyTaskRepository.existsById(id);
    }

    @Override
    public long count() {
        return dailyTaskRepository.count();
    }
}
