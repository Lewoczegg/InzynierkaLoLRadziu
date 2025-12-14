package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.AssignmentEntityMySQL;

import java.util.List;

@Repository
public interface AssignmentRepositoryMySQL extends JpaRepository<AssignmentEntityMySQL, Long> {
    AssignmentEntityMySQL findByAssignmentId(Long id);
    List<AssignmentEntityMySQL> findByLessonLessonId(Long lessonId);
}
