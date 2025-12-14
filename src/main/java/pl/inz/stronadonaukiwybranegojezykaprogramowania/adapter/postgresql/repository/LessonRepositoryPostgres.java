package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.LessonEntityPostgres;

import java.util.List;

@Repository
public interface LessonRepositoryPostgres extends JpaRepository<LessonEntityPostgres, Long> {
    List<LessonEntityPostgres> findByCourseCourseId(Long courseId);
    long countByCourse_CourseId(Long courseId);
}
