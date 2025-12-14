package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.CommentEntityMySQL;

@Repository
public interface CommentRepositoryMySQL extends JpaRepository<CommentEntityMySQL, Long> {
}
