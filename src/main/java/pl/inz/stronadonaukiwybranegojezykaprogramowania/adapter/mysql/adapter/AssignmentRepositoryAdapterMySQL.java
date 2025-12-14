package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.AssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.AssignmentMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.AssignmentRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssignmentRepositoryAdapterMySQL implements AssignmentRepositoryAdapter {

    private final AssignmentRepositoryMySQL assignmentRepository;
    private final AssignmentMapperMySQL assignmentMapper;

    public AssignmentRepositoryAdapterMySQL(AssignmentRepositoryMySQL assignmentRepository, AssignmentMapperMySQL assignmentMapper) {
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
