package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.DailyTaskResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.DailyTaskResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * In MongoDB, daily task results are stored as embedded documents in user documents.
 * This is a placeholder adapter that needs to be properly implemented based on the domain model.
 */
@Profile("mongodb")
@Component
public class DailyTaskResultRepositoryAdapterMongoDB implements DailyTaskResultRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public DailyTaskResultRepositoryAdapterMongoDB(UserRepositoryMongoDB userRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndDailyTask(UserDomain user, DailyTaskDomain dailyTask) {
        // TODO: Implement - search user's dailyTasks array for matching task
        return Optional.empty();
    }

    @Override
    public List<Object[]> findTotalPointsForAllUsers() {
        // TODO: Implement - aggregate points across all users
        return List.of();
    }

    @Override
    public Optional<DailyTaskResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        // TODO: Implement - search user's dailyTasks with date range
        return Optional.empty();
    }

    @Override
    public DailyTaskResultDomain save(DailyTaskResultDomain dailyTaskResult) {
        // TODO: Implement - update user's dailyTasks array
        return dailyTaskResult;
    }

    @Override
    public Optional<DailyTaskResultDomain> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DailyTaskResultDomain> findAll() {
        // TODO: Implement - collect all dailyTask assignments from all users
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        // No-op
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return userRepository.findAll().stream()
                .mapToLong(user -> user.getDailyTasks().size())
                .sum();
    }
}
