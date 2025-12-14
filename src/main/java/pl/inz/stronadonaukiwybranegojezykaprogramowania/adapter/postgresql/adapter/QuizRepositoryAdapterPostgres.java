package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.QuizMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.QuizRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QuizRepositoryAdapterPostgres implements QuizRepositoryAdapter {

    private final QuizRepositoryPostgres quizRepository;
    private final QuizMapperPostgres quizMapper;

    public QuizRepositoryAdapterPostgres(QuizRepositoryPostgres quizRepository, QuizMapperPostgres quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public List<QuizDomain> findAllNotCompletedByUser(Long userId) {
        return quizRepository.findAllNotCompletedByUser(userId).stream()
                .map(quizMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDomain save(QuizDomain quiz) {
        return quizMapper.toDomain(
                quizRepository.save(quizMapper.toEntity(quiz))
        );
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
