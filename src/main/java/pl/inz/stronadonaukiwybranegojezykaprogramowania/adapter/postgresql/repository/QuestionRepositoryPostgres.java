package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.QuestionEntityPostgres;

@Repository
public interface QuestionRepositoryPostgres extends JpaRepository<QuestionEntityPostgres, Long> {
}
