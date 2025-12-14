package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SolutionsRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.SolutionsMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.SolutionsRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SolutionsRepositoryAdapterMySQL implements SolutionsRepositoryAdapter {

    private final SolutionsRepositoryMySQL solutionsRepository;
    private final SolutionsMapperMySQL solutionsMapper;

    public SolutionsRepositoryAdapterMySQL(SolutionsRepositoryMySQL solutionsRepository, SolutionsMapperMySQL solutionsMapper) {
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
