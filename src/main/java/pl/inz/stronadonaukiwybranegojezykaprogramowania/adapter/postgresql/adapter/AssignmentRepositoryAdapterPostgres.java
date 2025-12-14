package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.AssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.AssignmentMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.AssignmentRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class AssignmentRepositoryAdapterPostgres implements AssignmentRepositoryAdapter {

    private final AssignmentRepositoryPostgres assignmentRepository;
    private final AssignmentMapperPostgres assignmentMapper;

    public AssignmentRepositoryAdapterPostgres(AssignmentRepositoryPostgres assignmentRepository, AssignmentMapperPostgres assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public AssignmentDomain findByAssignmentId(Long id) {
        return assignmentMapper.toDomain(
                assignmentRepository.findByAssignmentId(id)
        );
    }

    @Override
    public List<AssignmentDomain> findByLessonLessonId(Long lessonId) {
        return assignmentRepository.findByLessonLessonId(lessonId).stream()
                .map(assignmentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDomain save(AssignmentDomain assignment) {
        return assignmentMapper.toDomain(
                assignmentRepository.save(assignmentMapper.toEntity(assignment))
        );
    }

    @Override
    public Optional<AssignmentDomain> findById(Long id) {
        return assignmentRepository.findById(id)
                .map(assignmentMapper::toDomain);
    }

    @Override
    public List<AssignmentDomain> findAll() {
        return assignmentRepository.findAll().stream()
                .map(assignmentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return assignmentRepository.existsById(id);
    }

    @Override
    public long count() {
        return assignmentRepository.count();
    }
}
