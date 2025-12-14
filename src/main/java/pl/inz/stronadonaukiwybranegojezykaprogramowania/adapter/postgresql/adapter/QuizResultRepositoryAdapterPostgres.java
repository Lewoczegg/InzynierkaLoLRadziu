package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizResultRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.QuizMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.QuizResultMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.UserMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.QuizResultRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class QuizResultRepositoryAdapterPostgres implements QuizResultRepositoryAdapter {

    private final QuizResultRepositoryPostgres quizResultRepository;
    private final QuizResultMapperPostgres quizResultMapper;
    private final UserMapperPostgres userMapper;
    private final QuizMapperPostgres quizMapper;

    public QuizResultRepositoryAdapterPostgres(
            QuizResultRepositoryPostgres quizResultRepository,
            QuizResultMapperPostgres quizResultMapper,
            UserMapperPostgres userMapper,
            QuizMapperPostgres quizMapper) {
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
