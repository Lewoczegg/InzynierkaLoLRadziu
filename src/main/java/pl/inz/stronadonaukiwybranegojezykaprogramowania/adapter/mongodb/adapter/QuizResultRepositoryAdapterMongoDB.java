package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizResultEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.UserDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.QuizResultMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.QuizMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.QuizRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class QuizResultRepositoryAdapterMongoDB implements QuizResultRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final QuizRepositoryMongoDB quizRepository;
    private final QuizResultMapperMongoDB quizResultMapper;
    private final UserMapperMongoDB userMapper;
    private final QuizMapperMongoDB quizMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public QuizResultRepositoryAdapterMongoDB(UserRepositoryMongoDB userRepository,
                                             QuizRepositoryMongoDB quizRepository,
                                             QuizResultMapperMongoDB quizResultMapper,
                                             UserMapperMongoDB userMapper,
                                             QuizMapperMongoDB quizMapper,
                                             SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.quizResultMapper = quizResultMapper;
        this.userMapper = userMapper;
        this.quizMapper = quizMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<QuizResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime start, LocalDateTime end) {
        return userRepository.findById(user.getUserId())
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    return userDoc.getQuizResults().stream()
                            .filter(qr -> qr.getCompletedAt() != null)
                            .filter(qr -> {
                                LocalDateTime completedAt = qr.getCompletedAt().atZone(ZoneId.systemDefault()).toLocalDateTime();
                                return !completedAt.isBefore(start) && !completedAt.isAfter(end);
                            })
                            .findFirst()
                            .flatMap(qr -> quizRepository.findById(qr.getQuizId())
                                    .map(quizDoc -> {
                                        QuizDomain quizDomain = quizMapper.toDomain(quizDoc);
                                        return quizResultMapper.toDomain(qr, userDomain, quizDomain);
                                    }));
                });
    }

    @Override
    public Optional<QuizResultDomain> findByUserAndQuiz(UserDomain user, pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain quiz) {
        return userRepository.findById(user.getUserId())
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    if (userDoc.getQuizResults() == null) {
                        return Optional.empty();
                    }
                    return userDoc.getQuizResults().stream()
                            .filter(qr -> qr.getQuizId().equals(quiz.getQuizId()))
                            .findFirst()
                            .flatMap(qr -> quizRepository.findById(qr.getQuizId())
                                    .map(quizDoc -> {
                                        QuizDomain quizDomain = quizMapper.toDomain(quizDoc);
                                        return quizResultMapper.toDomain(qr, userDomain, quizDomain);
                                    }));
                });
    }

    @Override
    public boolean existsByUserAndQuiz(UserDomain user, pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain quiz) {
        return userRepository.findById(user.getUserId())
                .map(userDoc -> userDoc.getQuizResults() != null && userDoc.getQuizResults().stream()
                        .anyMatch(qr -> qr.getQuizId().equals(quiz.getQuizId())))
                .orElse(false);
    }

    @Override
    public List<Object[]> findTotalPointsForAllUsers() {
        return userRepository.findAll().stream()
                .map(userDoc -> {
                    if (userDoc.getQuizResults() == null) {
                        return new Object[]{userDoc.getUsername(), 0L};
                    }
                    
                    Long totalPoints = userDoc.getQuizResults().stream()
                            .filter(result -> result.getPoints() != null)
                            .mapToLong(QuizResultEmbedded::getPoints)
                            .sum();
                    
                    return new Object[]{userDoc.getUsername(), totalPoints};
                })
                .collect(Collectors.toList());
    }

    @Override
    public QuizResultDomain save(QuizResultDomain quizResult) {
        Long userId = quizResult.getUser().getUserId();
        
        Optional<UserDocument> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + userId);
        }
        
        UserDocument userDoc = userOpt.get();
        List<QuizResultEmbedded> results = userDoc.getQuizResults();
        
        // Initialize list if null
        if (results == null) {
            results = new java.util.ArrayList<>();
            userDoc.setQuizResults(results);
        }
        
        // Generate ID if new quiz result
        if (quizResult.getQuizResultId() == null) {
            quizResult.setQuizResultId(sequenceGeneratorService.generateSequence("quiz_result_sequence"));
        }
        
        // Add or update quiz result
        results.add(quizResultMapper.toEmbedded(quizResult));
        
        userRepository.save(userDoc);
        return quizResult;
    }

    @Override
    public Optional<QuizResultDomain> findById(Long id) {
        // QuizResult doesn't have a separate ID in MongoDB
        return Optional.empty();
    }

    @Override
    public List<QuizResultDomain> findAll() {
        return userRepository.findAll().stream()
                .flatMap(userDoc -> {
                    UserDomain userDomain = userMapper.toDomain(userDoc);
                    return userDoc.getQuizResults().stream()
                            .flatMap(qr -> quizRepository.findById(qr.getQuizId())
                                    .map(quizDoc -> {
                                        QuizDomain quizDomain = quizMapper.toDomain(quizDoc);
                                        return quizResultMapper.toDomain(qr, userDomain, quizDomain);
                                    }).stream());
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
                .mapToLong(userDoc -> userDoc.getQuizResults().size())
                .sum();
    }
}
