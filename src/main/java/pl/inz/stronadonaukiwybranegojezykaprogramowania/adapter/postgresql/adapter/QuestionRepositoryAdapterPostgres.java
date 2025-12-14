package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuestionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.QuestionMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.QuestionRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class QuestionRepositoryAdapterPostgres implements QuestionRepositoryAdapter {

    private final QuestionRepositoryPostgres questionRepository;
    private final QuestionMapperPostgres questionMapper;

    public QuestionRepositoryAdapterPostgres(QuestionRepositoryPostgres questionRepository, QuestionMapperPostgres questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionDomain save(QuestionDomain question) {
        return questionMapper.toDomain(
                questionRepository.save(questionMapper.toEntity(question))
        );
    }

    @Override
    public Optional<QuestionDomain> findById(Long id) {
        return questionRepository.findById(id)
                .map(questionMapper::toDomain);
    }

    @Override
    public List<QuestionDomain> findAll() {
        return questionRepository.findAll().stream()
                .map(questionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return questionRepository.existsById(id);
    }

    @Override
    public long count() {
        return questionRepository.count();
    }
}
