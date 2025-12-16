package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.DailyTaskAssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.DailyTaskAssignmentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.DailyTaskMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.DailyTaskRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In MongoDB, daily task results are stored as embedded documents in user documents.
 * Each user has a list of DailyTaskAssignmentEmbedded which contains taskId, assignmentDate, completedAt, and points.
 */
@Profile("mongodb")
@Component
public class DailyTaskResultRepositoryAdapterMongoDB implements DailyTaskResultRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final DailyTaskRepositoryMongoDB dailyTaskRepository;
    private final DailyTaskMapperMongoDB dailyTaskMapper;
    private final UserMapperMongoDB userMapper;
    private final DailyTaskAssignmentMapperMongoDB assignmentMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public DailyTaskResultRepositoryAdapterMongoDB(
            UserRepositoryMongoDB userRepository,
            DailyTaskRepositoryMongoDB dailyTaskRepository,
            DailyTaskMapperMongoDB dailyTaskMapper,
            UserMapperMongoDB userMapper,
            DailyTaskAssignmentMapperMongoDB assignmentMapper,
            SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.dailyTaskRepository = dailyTaskRepository;
        this.dailyTaskMapper = dailyTaskMapper;
        this.userMapper = userMapper;
        this.assignmentMapper = assignmentMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndDailyTask(UserDomain user, DailyTaskDomain dailyTask) {
        return userRepository.findById(user.getUserId())
                .flatMap(userDoc -> {
                    if (userDoc.getDailyTasks() == null) {
                        return Optional.empty();
                    }
                    
                    return userDoc.getDailyTasks().stream()
                            .filter(task -> task.getTaskId().equals(dailyTask.getTaskId()))
                            .findFirst()
                            .map(embedded -> mapToDailyTaskResultDomain(embedded, user, dailyTask));
                });
    }

    @Override
    public List<Object[]> findTotalPointsForAllUsers() {
        return userRepository.findAll().stream()
                .map(userDoc -> {
                    if (userDoc.getDailyTasks() == null) {
                        return new Object[]{userDoc.getUsername(), 0L};
                    }
                    
                    Long totalPoints = userDoc.getDailyTasks().stream()
                            .filter(task -> task.getPoints() != null && task.getCompletedAt() != null)
                            .mapToLong(DailyTaskAssignmentEmbedded::getPoints)
                            .sum();
                    
                    return new Object[]{userDoc.getUsername(), totalPoints};
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        return userRepository.findById(user.getUserId())
                .flatMap(userDoc -> {
                    if (userDoc.getDailyTasks() == null) {
                        return Optional.empty();
                    }
                    
                    var startInstant = startOfDay.atZone(ZoneId.systemDefault()).toInstant();
                    var endInstant = endOfDay.atZone(ZoneId.systemDefault()).toInstant();
                    
                    return userDoc.getDailyTasks().stream()
                            .filter(task -> task.getCompletedAt() != null &&
                                    !task.getCompletedAt().isBefore(startInstant) &&
                                    !task.getCompletedAt().isAfter(endInstant))
                            .findFirst()
                            .flatMap(embedded -> {
                                var dailyTask = dailyTaskRepository.findById(embedded.getTaskId())
                                        .map(dailyTaskMapper::toDomain);
                                
                                if (dailyTask.isPresent()) {
                                    return Optional.of(mapToDailyTaskResultDomain(embedded, user, dailyTask.get()));
                                }
                                return Optional.empty();
                            });
                });
    }

    @Override
    public DailyTaskResultDomain save(DailyTaskResultDomain dailyTaskResult) {
        var userDoc = userRepository.findById(dailyTaskResult.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + dailyTaskResult.getUser().getUserId()));
        
        if (userDoc.getDailyTasks() == null) {
            userDoc.setDailyTasks(new ArrayList<>());
        }
        
        var taskId = dailyTaskResult.getDailyTask().getTaskId();
        var embedded = new DailyTaskAssignmentEmbedded();
        embedded.setTaskId(taskId);
        embedded.setCompletedAt(dailyTaskResult.getCompletedAt() != null ? 
                dailyTaskResult.getCompletedAt().atZone(ZoneId.systemDefault()).toInstant() : null);
        embedded.setPoints(dailyTaskResult.getPoints());
        
        // Check if task already exists for this user
        var existingTask = userDoc.getDailyTasks().stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst();
        
        if (existingTask.isPresent()) {
            // Update existing
            var idx = userDoc.getDailyTasks().indexOf(existingTask.get());
            userDoc.getDailyTasks().set(idx, embedded);
        } else {
            // Add new
            userDoc.getDailyTasks().add(embedded);
        }
        
        userRepository.save(userDoc);
        return dailyTaskResult;
    }

    @Override
    public Optional<DailyTaskResultDomain> findById(Long id) {
        // In MongoDB, daily task results don't have independent IDs, they're embedded in users
        // This method is not applicable for MongoDB
        return Optional.empty();
    }

    @Override
    public List<DailyTaskResultDomain> findAll() {
        return userRepository.findAll().stream()
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    
                    if (userDoc.getDailyTasks() == null) {
                        return java.util.stream.Stream.empty();
                    }
                    
                    return userDoc.getDailyTasks().stream()
                            .flatMap(embedded -> {
                                var dailyTask = dailyTaskRepository.findById(embedded.getTaskId())
                                        .map(dailyTaskMapper::toDomain);
                                
                                if (dailyTask.isPresent()) {
                                    return java.util.stream.Stream.of(mapToDailyTaskResultDomain(embedded, userDomain, dailyTask.get()));
                                }
                                return java.util.stream.Stream.empty();
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // In MongoDB, daily task results don't have independent IDs
        // This would need to be called with user context
    }

    @Override
    public boolean existsById(Long id) {
        // In MongoDB, daily task results don't have independent IDs
        return false;
    }

    @Override
    public long count() {
        return userRepository.findAll().stream()
                .mapToLong(user -> user.getDailyTasks() != null ? user.getDailyTasks().size() : 0)
                .sum();
    }

    private DailyTaskResultDomain mapToDailyTaskResultDomain(
            DailyTaskAssignmentEmbedded embedded,
            UserDomain user,
            DailyTaskDomain dailyTask) {
        DailyTaskResultDomain result = new DailyTaskResultDomain();
        result.setUser(user);
        result.setDailyTask(dailyTask);
        result.setPoints(embedded.getPoints());
        
        if (embedded.getCompletedAt() != null) {
            result.setCompletedAt(LocalDateTime.ofInstant(
                    embedded.getCompletedAt(),
                    ZoneId.systemDefault()
            ));
        }
        
        return result;
    }
}
