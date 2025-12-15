package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.DailyTaskMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.DailyTaskRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class DailyTaskRepositoryAdapterMongoDB implements DailyTaskRepositoryAdapter {

    private final DailyTaskRepositoryMongoDB dailyTaskRepository;
    private final UserRepositoryMongoDB userRepository;
    private final DailyTaskMapperMongoDB dailyTaskMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public DailyTaskRepositoryAdapterMongoDB(DailyTaskRepositoryMongoDB dailyTaskRepository,
                                            UserRepositoryMongoDB userRepository,
                                            DailyTaskMapperMongoDB dailyTaskMapper,
                                            SequenceGeneratorService sequenceGeneratorService) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userRepository = userRepository;
        this.dailyTaskMapper = dailyTaskMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<DailyTaskDomain> findAllNotCompletedByUser(Long userId) {
        // Get all tasks completed by the user
        var completedTaskIds = userRepository.findById(userId)
                .map(userDoc -> userDoc.getDailyTasks() != null ? userDoc.getDailyTasks().stream()
                        .filter(dt -> dt.getCompletedAt() != null)
                        .map(dt -> dt.getTaskId())
                        .collect(Collectors.toSet()) : java.util.Collections.emptySet())
                .orElse(java.util.Collections.emptySet());
        
        // Return all tasks not in the completed set
        return dailyTaskRepository.findAll().stream()
                .filter(taskDoc -> !completedTaskIds.contains(taskDoc.getId()))
                .map(dailyTaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public DailyTaskDomain save(DailyTaskDomain dailyTask) {
        var dailyTaskDoc = dailyTaskMapper.toDocument(dailyTask);
        if (dailyTaskDoc.getId() == null) {
            dailyTaskDoc.setId(sequenceGeneratorService.generateSequence("daily_tasks"));
        }
        return dailyTaskMapper.toDomain(dailyTaskRepository.save(dailyTaskDoc));
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
