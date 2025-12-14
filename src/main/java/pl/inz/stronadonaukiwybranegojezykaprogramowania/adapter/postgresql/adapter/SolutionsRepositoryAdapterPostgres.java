package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SolutionsRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.SolutionsMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.SolutionsRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SolutionsRepositoryAdapterPostgres implements SolutionsRepositoryAdapter {

    private final SolutionsRepositoryPostgres solutionsRepository;
    private final SolutionsMapperPostgres solutionsMapper;

    public SolutionsRepositoryAdapterPostgres(SolutionsRepositoryPostgres solutionsRepository, SolutionsMapperPostgres solutionsMapper) {
        this.solutionsRepository = solutionsRepository;
        this.solutionsMapper = solutionsMapper;
    }

    @Override
    public List<SolutionsDomain> findByAssignment_AssignmentId(Long assignmentId) {
        return solutionsRepository.findByAssignment_AssignmentId(assignmentId).stream()
                .map(solutionsMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public SolutionsDomain save(SolutionsDomain solutions) {
        return solutionsMapper.toDomain(
                solutionsRepository.save(solutionsMapper.toEntity(solutions))
        );
    }

    @Override
    public Optional<SolutionsDomain> findById(Long id) {
        return solutionsRepository.findById(id)
                .map(solutionsMapper::toDomain);
    }

    @Override
    public List<SolutionsDomain> findAll() {
        return solutionsRepository.findAll().stream()
                .map(solutionsMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        solutionsRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return solutionsRepository.existsById(id);
    }

    @Override
    public long count() {
        return solutionsRepository.count();
    }
}
