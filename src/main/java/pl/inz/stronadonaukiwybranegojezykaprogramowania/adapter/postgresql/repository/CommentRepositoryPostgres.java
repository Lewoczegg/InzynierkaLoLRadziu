package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.CommentEntityPostgres;

@Repository
public interface CommentRepositoryPostgres extends JpaRepository<CommentEntityPostgres, Long> {
}
