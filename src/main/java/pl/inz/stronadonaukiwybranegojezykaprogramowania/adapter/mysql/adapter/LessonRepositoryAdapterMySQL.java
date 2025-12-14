package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.LessonRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.LessonMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.LessonRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class LessonRepositoryAdapterMySQL implements LessonRepositoryAdapter {

    private final LessonRepositoryMySQL lessonRepository;
    private final LessonMapperMySQL lessonMapper;

    public LessonRepositoryAdapterMySQL(LessonRepositoryMySQL lessonRepository, LessonMapperMySQL lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<LessonDomain> findByCourseCourseId(Long courseId) {
        return lessonRepository.findByCourseCourseId(courseId).stream()
                .map(lessonMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCourse_CourseId(Long courseId) {
        return lessonRepository.countByCourse_CourseId(courseId);
    }

    @Override
    public LessonDomain save(LessonDomain lesson) {
        return lessonMapper.toDomain(
                lessonRepository.save(lessonMapper.toEntity(lesson))
        );
    }

    @Override
    public Optional<LessonDomain> findById(Long id) {
        return lessonRepository.findById(id)
                .map(lessonMapper::toDomain);
    }

    @Override
    public List<LessonDomain> findAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return lessonRepository.existsById(id);
    }

    @Override
    public long count() {
        return lessonRepository.count();
    }
}
