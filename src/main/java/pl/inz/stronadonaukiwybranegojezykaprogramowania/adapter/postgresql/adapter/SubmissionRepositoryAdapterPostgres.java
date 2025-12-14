package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SubmissionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.SubmissionMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.SubmissionRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class SubmissionRepositoryAdapterPostgres implements SubmissionRepositoryAdapter {

    private final SubmissionRepositoryPostgres submissionRepository;
    private final SubmissionMapperPostgres submissionMapper;

    public SubmissionRepositoryAdapterPostgres(SubmissionRepositoryPostgres submissionRepository, SubmissionMapperPostgres submissionMapper) {
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
