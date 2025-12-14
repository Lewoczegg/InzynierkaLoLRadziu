package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.SubmissionEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;

@Component
public class SubmissionMapperPostgres {

    private final AssignmentMapperPostgres assignmentMapper;
    private final UserMapperPostgres userMapper;

    public SubmissionMapperPostgres(AssignmentMapperPostgres assignmentMapper, UserMapperPostgres userMapper) {
        this.assignmentMapper = assignmentMapper;
        this.userMapper = userMapper;
    }

    public SubmissionDomain toDomain(SubmissionEntityPostgres entity) {
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

    public SubmissionEntityPostgres toEntity(SubmissionDomain domain) {
        if (domain == null) {
            return null;
        }
        
        SubmissionEntityPostgres entity = new SubmissionEntityPostgres();
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
