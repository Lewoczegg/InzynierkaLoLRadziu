package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.LessonEntityMySQL;

import java.util.List;

@Repository
public interface LessonRepositoryMySQL extends JpaRepository<LessonEntityMySQL, Long> {
    List<LessonEntityMySQL> findByCourseCourseId(Long courseId);
    long countByCourse_CourseId(Long courseId);
}
