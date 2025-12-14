package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.SubmissionEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;

@Component
public class SubmissionMapperMySQL {

    private final AssignmentMapperMySQL assignmentMapper;
    private final UserMapperMySQL userMapper;

    public SubmissionMapperMySQL(AssignmentMapperMySQL assignmentMapper, UserMapperMySQL userMapper) {
        this.assignmentMapper = assignmentMapper;
        this.userMapper = userMapper;
    }

    public SubmissionDomain toDomain(SubmissionEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        SubmissionDomain domain = new SubmissionDomain();
        domain.setSubmissionId(entity.getSubmissionId());
        domain.setAssignment(assignmentMapper.toDomain(entity.getAssignment()));
        domain.setUser(userMapper.toDomain(entity.getUser()));
        domain.setContent(entity.getContent());
        domain.setGrade(entity.getGrade());
        domain.setSubmittedAt(entity.getSubmittedAt());
        domain.setGradedAt(entity.getGradedAt());
        return domain;
    }

    public SubmissionEntityMySQL toEntity(SubmissionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        SubmissionEntityMySQL entity = new SubmissionEntityMySQL();
        entity.setSubmissionId(domain.getSubmissionId());
        entity.setAssignment(assignmentMapper.toEntity(domain.getAssignment()));
        entity.setUser(userMapper.toEntity(domain.getUser()));
        entity.setContent(domain.getContent());
        entity.setGrade(domain.getGrade());
        entity.setSubmittedAt(domain.getSubmittedAt());
        entity.setGradedAt(domain.getGradedAt());
        return entity;
    }
}
