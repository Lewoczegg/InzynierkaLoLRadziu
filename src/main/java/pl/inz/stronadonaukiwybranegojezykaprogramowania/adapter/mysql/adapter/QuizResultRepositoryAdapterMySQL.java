package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.QuizMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.QuizResultMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.UserMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.QuizResultRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QuizResultRepositoryAdapterMySQL implements QuizResultRepositoryAdapter {

    private final QuizResultRepositoryMySQL quizResultRepository;
    private final QuizResultMapperMySQL quizResultMapper;
    private final UserMapperMySQL userMapper;
    private final QuizMapperMySQL quizMapper;

    public QuizResultRepositoryAdapterMySQL(
            QuizResultRepositoryMySQL quizResultRepository,
            QuizResultMapperMySQL quizResultMapper,
            UserMapperMySQL userMapper,
            QuizMapperMySQL quizMapper) {
        this.quizResultRepository = quizResultRepository;
        this.quizResultMapper = quizResultMapper;
        this.userMapper = userMapper;
        this.quizMapper = quizMapper;
    }

    @Override
    public Optional<QuizResultDomain> findByUserAndCompletedAtBetween(UserDomain user, LocalDateTime start, LocalDateTime end) {
        return quizResultRepository.findByUserAndCompletedAtBetween(
                userMapper.toEntity(user),
                start,
                end
        ).map(quizResultMapper::toDomain);
    }

    @Override
    public Optional<QuizResultDomain> findByUserAndQuiz(UserDomain user, QuizDomain quiz) {
        return quizResultRepository.findByUserAndQuiz(
                userMapper.toEntity(user),
                quizMapper.toEntity(quiz)
        ).map(quizResultMapper::toDomain);
    }

    @Override
    public boolean existsByUserAndQuiz(UserDomain user, QuizDomain quiz) {
        return quizResultRepository.existsByUserAndQuiz(
                userMapper.toEntity(user),
                quizMapper.toEntity(quiz)
        );
    }

    @Override
    public List<Object[]> findTotalPointsForAllUsers() {
        return quizResultRepository.findTotalPointsForAllUsers();
    }

    @Override
    public QuizResultDomain save(QuizResultDomain quizResult) {
        return quizResultMapper.toDomain(
                quizResultRepository.save(quizResultMapper.toEntity(quizResult))
        );
    }

    @Override
    public Optional<QuizResultDomain> findById(Long id) {
        return quizResultRepository.findById(id)
                .map(quizResultMapper::toDomain);
    }

    @Override
    public List<QuizResultDomain> findAll() {
        return quizResultRepository.findAll().stream()
                .map(quizResultMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        quizResultRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return quizResultRepository.existsById(id);
    }

    @Override
    public long count() {
        return quizResultRepository.count();
    }
}
