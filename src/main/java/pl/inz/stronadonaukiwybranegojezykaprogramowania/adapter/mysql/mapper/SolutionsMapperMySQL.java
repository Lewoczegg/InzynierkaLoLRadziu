package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.SolutionsEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;

@Component
public class SolutionsMapperMySQL {

    private final AssignmentMapperMySQL assignmentMapper;

    public SolutionsMapperMySQL(AssignmentMapperMySQL assignmentMapper) {
        this.assignmentMapper = assignmentMapper;
    }

    public SolutionsDomain toDomain(SolutionsEntityMySQL entity) {
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

    public SolutionsEntityMySQL toEntity(SolutionsDomain domain) {
        if (domain == null) {
            return null;
        }
        
        SolutionsEntityMySQL entity = new SolutionsEntityMySQL();
        entity.setSolutionId(domain.getSolutionId());
        entity.setAssignment(assignmentMapper.toEntity(domain.getAssignment()));
        entity.setContent(domain.getContent());
        entity.setLanguage(domain.getLanguage());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}
