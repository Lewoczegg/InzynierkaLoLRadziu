package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SubmissionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.SubmissionMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.SubmissionRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubmissionRepositoryAdapterMySQL implements SubmissionRepositoryAdapter {

    private final SubmissionRepositoryMySQL submissionRepository;
    private final SubmissionMapperMySQL submissionMapper;

    public SubmissionRepositoryAdapterMySQL(SubmissionRepositoryMySQL submissionRepository, SubmissionMapperMySQL submissionMapper) {
        this.submissionRepository = submissionRepository;
        this.submissionMapper = submissionMapper;
    }

    @Override
    public Optional<SubmissionDomain> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId) {
        return submissionRepository.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(userId, assignmentId)
                .map(submissionMapper::toDomain);
    }

    @Override
    public long countDistinctAssignmentsByUserUserId(Long userId) {
        return submissionRepository.countDistinctAssignmentsByUserUserId(userId);
    }

    @Override
    public SubmissionDomain save(SubmissionDomain submission) {
        return submissionMapper.toDomain(
                submissionRepository.save(submissionMapper.toEntity(submission))
        );
    }

    @Override
    public Optional<SubmissionDomain> findById(Long id) {
        return submissionRepository.findById(id)
                .map(submissionMapper::toDomain);
    }

    @Override
    public List<SubmissionDomain> findAll() {
        return submissionRepository.findAll().stream()
                .map(submissionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        submissionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return submissionRepository.existsById(id);
    }

    @Override
    public long count() {
        return submissionRepository.count();
    }
}
