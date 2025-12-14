package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.SolutionsEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

@Component
public class SolutionsMapperPostgres {

    private final AssignmentMapperPostgres assignmentMapper;

    public SolutionsMapperPostgres(AssignmentMapperPostgres assignmentMapper) {
        this.assignmentMapper = assignmentMapper;
    }

    public SolutionsDomain toDomain(SolutionsEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        SolutionsDomain domain = new SolutionsDomain();
        domain.setSolutionId(entity.getSolutionId());
        domain.setAssignment(assignmentMapper.toDomain(entity.getAssignment()));
        domain.setContent(entity.getContent());
        domain.setLanguage(entity.getLanguage());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public SolutionsEntityPostgres toEntity(SolutionsDomain domain) {
        if (domain == null) {
            return null;
        }
        
        SolutionsEntityPostgres entity = new SolutionsEntityPostgres();
        entity.setSolutionId(domain.getSolutionId());
        entity.setAssignment(assignmentMapper.toEntity(domain.getAssignment()));
        entity.setContent(domain.getContent());
        entity.setLanguage(domain.getLanguage());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}
