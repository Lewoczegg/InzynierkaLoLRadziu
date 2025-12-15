package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class UserRepositoryAdapterMongoDB implements UserRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final UserMapperMongoDB userMapper;
    private final SequenceGeneratorService sequenceGeneratorService;
    // TODO: Will need RoleRepositoryAdapter to fetch roles

    public UserRepositoryAdapterMongoDB(UserRepositoryMongoDB userRepository, UserMapperMongoDB userMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public UserDomain findByUsername(String username) {
        var userDoc = userRepository.findByUsername(username);
        if (userDoc == null) {
            return null;
        }
        // TODO: Fetch role based on roleIdLegacy
        RoleDomain role = null; // Placeholder
        return userMapper.toDomain(userDoc);
    }

    @Override
    public UserDomain save(UserDomain user) {
        var userDoc = userMapper.toDocument(user);
        if (userDoc.getId() == null) {
            userDoc.setId(sequenceGeneratorService.generateSequence("users"));
        }
        // Initialize empty lists if null to prevent NullPointerExceptions
        if (userDoc.getProgress() == null) {
            userDoc.setProgress(new java.util.ArrayList<>());
        }
        if (userDoc.getQuizResults() == null) {
            userDoc.setQuizResults(new java.util.ArrayList<>());
        }
        if (userDoc.getDailyTasks() == null) {
            userDoc.setDailyTasks(new java.util.ArrayList<>());
        }
        return userMapper.toDomain(userRepository.save(userDoc));
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
		// TODO: Fetch role based on roleIdLegacy
		return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public List<UserDomain> findAll() {
		// TODO: Fetch role based on roleIdLegacy
		return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
