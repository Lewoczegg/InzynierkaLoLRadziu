package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CommentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.CommentMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.CommentRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommentRepositoryAdapterPostgres implements CommentRepositoryAdapter {

    private final CommentRepositoryPostgres commentRepository;
    private final CommentMapperPostgres commentMapper;

    public CommentRepositoryAdapterPostgres(CommentRepositoryPostgres commentRepository, CommentMapperPostgres commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDomain save(CommentDomain comment) {
        return commentMapper.toDomain(
                commentRepository.save(commentMapper.toEntity(comment))
        );
    }

    @Override
    public Optional<CommentDomain> findById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDomain);
    }

    @Override
    public List<CommentDomain> findAll() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return commentRepository.existsById(id);
    }

    @Override
    public long count() {
        return commentRepository.count();
    }
}
