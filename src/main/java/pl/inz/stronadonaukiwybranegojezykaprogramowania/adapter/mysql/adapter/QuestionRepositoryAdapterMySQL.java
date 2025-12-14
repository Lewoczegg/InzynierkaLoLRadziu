package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuestionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.QuestionMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.QuestionRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class QuestionRepositoryAdapterMySQL implements QuestionRepositoryAdapter {

    private final QuestionRepositoryMySQL questionRepository;
    private final QuestionMapperMySQL questionMapper;

    public QuestionRepositoryAdapterMySQL(QuestionRepositoryMySQL questionRepository, QuestionMapperMySQL questionMapper) {
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
