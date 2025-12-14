package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.ProgressEntityMySQL;

import java.util.Optional;

@Repository
public interface ProgressRepositoryMySQL extends JpaRepository<ProgressEntityMySQL, Long> {
    Optional<ProgressEntityMySQL> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId);
    Long countByUserUserId(Long userId);
    long countByUser_UserIdAndCompletedAtIsNotNull(Long userId);
    long countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(Long userId, Long courseId);
}
