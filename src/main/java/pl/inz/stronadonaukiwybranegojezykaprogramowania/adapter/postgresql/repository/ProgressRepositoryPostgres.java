package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.ProgressEntityPostgres;

import java.util.Optional;

@Repository
public interface ProgressRepositoryPostgres extends JpaRepository<ProgressEntityPostgres, Long> {
    Optional<ProgressEntityPostgres> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId);
    Long countByUserUserId(Long userId);
    long countByUser_UserIdAndCompletedAtIsNotNull(Long userId);
    long countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(Long userId, Long courseId);
}
