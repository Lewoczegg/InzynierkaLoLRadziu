package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.QuizMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.QuizRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
@Transactional
public class QuizRepositoryAdapterMySQL implements QuizRepositoryAdapter {

    private final QuizRepositoryMySQL quizRepository;
    private final QuizMapperMySQL quizMapper;

    public QuizRepositoryAdapterMySQL(QuizRepositoryMySQL quizRepository, QuizMapperMySQL quizMapper) {
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
