package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskAssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.DailyTaskAssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.UserDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.DailyTaskAssignmentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.DailyTaskMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.DailyTaskRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskAssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class DailyTaskAssignmentRepositoryAdapterMongoDB implements DailyTaskAssignmentRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final DailyTaskRepositoryMongoDB dailyTaskRepository;
    private final DailyTaskAssignmentMapperMongoDB dailyTaskAssignmentMapper;
    private final UserMapperMongoDB userMapper;
    private final DailyTaskMapperMongoDB dailyTaskMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public DailyTaskAssignmentRepositoryAdapterMongoDB(UserRepositoryMongoDB userRepository,
                                                       DailyTaskRepositoryMongoDB dailyTaskRepository,
                                                       DailyTaskAssignmentMapperMongoDB dailyTaskAssignmentMapper,
                                                       UserMapperMongoDB userMapper,
                                                       DailyTaskMapperMongoDB dailyTaskMapper,
                                                       SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.dailyTaskRepository = dailyTaskRepository;
        this.dailyTaskAssignmentMapper = dailyTaskAssignmentMapper;
        this.userMapper = userMapper;
        this.dailyTaskMapper = dailyTaskMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<DailyTaskAssignmentDomain> findByUserAndAssignmentDate(UserDomain user, java.time.LocalDate date) {
        return userRepository.findById(user.getUserId())
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    if (userDoc.getDailyTasks() == null) {
                        return Optional.empty();
                    }
                    return userDoc.getDailyTasks().stream()
                            .filter(dt -> dt.getAssignmentDate() != null)
                            .filter(dt -> {
                                java.time.LocalDate assignmentDate = dt.getAssignmentDate().atZone(ZoneId.systemDefault()).toLocalDate();
                                return assignmentDate.equals(date);
                            })
                            .map(dt -> {
                                DailyTaskDomain dailyTask = dailyTaskRepository.findById(dt.getTaskId())
                                        .map(dailyTaskMapper::toDomain)
                                        .orElse(null);
                                return dailyTaskAssignmentMapper.toDomain(dt, userDomain, dailyTask);
                            })
                            .findFirst();
                });
    }

    @Override
    public DailyTaskAssignmentDomain save(DailyTaskAssignmentDomain dailyTaskAssignment) {
        Long userId = dailyTaskAssignment.getUser().getUserId();
        
        Optional<UserDocument> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + userId);
        }
        
        UserDocument userDoc = userOpt.get();
        List<DailyTaskAssignmentEmbedded> tasks = userDoc.getDailyTasks();
        
        // Initialize list if null
        if (tasks == null) {
            tasks = new java.util.ArrayList<>();
            userDoc.setDailyTasks(tasks);
        }
        
        // Remove existing task assignment for the same task
        tasks.removeIf(t -> t.getTaskId().equals(dailyTaskAssignment.getDailyTask().getTaskId()));
        
        // Generate ID if new task assignment
        if (dailyTaskAssignment.getId() == null) {
            dailyTaskAssignment.setId(sequenceGeneratorService.generateSequence("daily_task_assignment_sequence"));
        }
        
        // Add new task assignment
        tasks.add(dailyTaskAssignmentMapper.toEmbedded(dailyTaskAssignment));
        
        userRepository.save(userDoc);
        return dailyTaskAssignment;
    }

    @Override
    public Optional<DailyTaskAssignmentDomain> findById(Long id) {
        // DailyTaskAssignment doesn't have a separate ID in MongoDB
        return Optional.empty();
    }

    @Override
    public List<DailyTaskAssignmentDomain> findAll() {
        return userRepository.findAll().stream()
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    return userDoc.getDailyTasks().stream()
                            .map(dt -> {
                                DailyTaskDomain dailyTask = dailyTaskRepository.findById(dt.getTaskId())
                                        .map(dailyTaskMapper::toDomain)
                                        .orElse(null);
                                return dailyTaskAssignmentMapper.toDomain(dt, userDomain, dailyTask);
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // Not applicable
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return userRepository.findAll().stream()
                .mapToLong(userDoc -> userDoc.getDailyTasks().size())
                .sum();
    }
}
