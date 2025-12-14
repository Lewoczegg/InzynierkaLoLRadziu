package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.SolutionsEntityPostgres;

import java.util.List;

@Repository
public interface SolutionsRepositoryPostgres extends JpaRepository<SolutionsEntityPostgres, Long> {
    List<SolutionsEntityPostgres> findByAssignment_AssignmentId(Long assignmentId);
}
