package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.SolutionsEntityMySQL;

import java.util.List;

@Repository
public interface SolutionsRepositoryMySQL extends JpaRepository<SolutionsEntityMySQL, Long> {
    List<SolutionsEntityMySQL> findByAssignment_AssignmentId(Long assignmentId);
}
