package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByAssignmentId (Long id);
    List<Assignment> findByLessonLessonId(Long lessonId);
}
