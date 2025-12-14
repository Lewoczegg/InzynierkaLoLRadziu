package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.util.List;
import java.util.Optional;

public interface LessonRepositoryAdapter {
    List<LessonDomain> findByCourseCourseId(Long courseId);
    long countByCourse_CourseId(Long courseId);
    LessonDomain save(LessonDomain lesson);
    Optional<LessonDomain> findById(Long id);
    List<LessonDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
