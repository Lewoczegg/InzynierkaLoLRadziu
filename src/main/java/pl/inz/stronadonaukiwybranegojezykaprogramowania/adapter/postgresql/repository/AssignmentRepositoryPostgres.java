package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.AssignmentEntityPostgres;

import java.util.List;

@Repository
public interface AssignmentRepositoryPostgres extends JpaRepository<AssignmentEntityPostgres, Long> {
    AssignmentEntityPostgres findByAssignmentId(Long id);
    List<AssignmentEntityPostgres> findByLessonLessonId(Long lessonId);
}
