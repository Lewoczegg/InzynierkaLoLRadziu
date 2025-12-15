package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.QuizMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.QuizRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class QuizRepositoryAdapterMongoDB implements QuizRepositoryAdapter {

    private final QuizRepositoryMongoDB quizRepository;
    private final UserRepositoryMongoDB userRepository;
    private final QuizMapperMongoDB quizMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public QuizRepositoryAdapterMongoDB(QuizRepositoryMongoDB quizRepository,
                                       UserRepositoryMongoDB userRepository,
                                       QuizMapperMongoDB quizMapper,
                                       SequenceGeneratorService sequenceGeneratorService) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.quizMapper = quizMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<QuizDomain> findAllNotCompletedByUser(Long userId) {
        // Get all quizzes completed by the user
        var completedQuizIds = userRepository.findById(userId)
                .map(userDoc -> userDoc.getQuizResults().stream()
                        .map(qr -> qr.getQuizId())
                        .collect(Collectors.toSet()))
                .orElse(java.util.Collections.emptySet());
        
        // Return all quizzes not in the completed set
        return quizRepository.findAll().stream()
                .filter(quizDoc -> !completedQuizIds.contains(quizDoc.getId()))
                .map(quizMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDomain save(QuizDomain quiz) {
        var quizDoc = quizMapper.toDocument(quiz);
        if (quizDoc.getId() == null) {
            quizDoc.setId(sequenceGeneratorService.generateSequence("quizzes"));
        }
        return quizMapper.toDomain(quizRepository.save(quizDoc));
    }

    @Override
    public Optional<QuizDomain> findById(Long id) {
        return quizRepository.findById(id)
                .map(quizMapper::toDomain);
    }

    @Override
    public List<QuizDomain> findAll() {
        return quizRepository.findAll().stream()
                .map(quizMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return quizRepository.existsById(id);
    }

    @Override
    public long count() {
        return quizRepository.count();
    }
}
