package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CommentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.CommentMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.CommentRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommentRepositoryAdapterMySQL implements CommentRepositoryAdapter {

    private final CommentRepositoryMySQL commentRepository;
    private final CommentMapperMySQL commentMapper;

    public CommentRepositoryAdapterMySQL(CommentRepositoryMySQL commentRepository, CommentMapperMySQL commentMapper) {
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
