package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;

import java.util.List;
import java.util.Optional;

public interface ProgressRepositoryAdapter {
    Optional<ProgressDomain> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId);
    Long countByUserUserId(Long userId);
    long countByUser_UserIdAndCompletedAtIsNotNull(Long userId);
    long countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(Long userId, Long courseId);
    ProgressDomain save(ProgressDomain progress);
    Optional<ProgressDomain> findById(Long id);
    List<ProgressDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
