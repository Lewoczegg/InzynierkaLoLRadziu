package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.ProgressRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.ProgressMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.ProgressRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class ProgressRepositoryAdapterMySQL implements ProgressRepositoryAdapter {

    private final ProgressRepositoryMySQL progressRepository;
    private final ProgressMapperMySQL progressMapper;

    public ProgressRepositoryAdapterMySQL(ProgressRepositoryMySQL progressRepository, ProgressMapperMySQL progressMapper) {
        this.progressRepository = progressRepository;
        this.progressMapper = progressMapper;
    }

    @Override
    public Optional<ProgressDomain> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId) {
        return progressRepository.findByUserUserIdAndLessonLessonId(userId, lessonId)
                .map(progressMapper::toDomain);
    }

    @Override
    public Long countByUserUserId(Long userId) {
        return progressRepository.countByUserUserId(userId);
    }

    @Override
    public long countByUser_UserIdAndCompletedAtIsNotNull(Long userId) {
        return progressRepository.countByUser_UserIdAndCompletedAtIsNotNull(userId);
    }

    @Override
    public long countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(Long userId, Long courseId) {
        return progressRepository.countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(userId, courseId);
    }

    @Override
    public ProgressDomain save(ProgressDomain progress) {
        return progressMapper.toDomain(
                progressRepository.save(progressMapper.toEntity(progress))
        );
    }

    @Override
    public Optional<ProgressDomain> findById(Long id) {
        return progressRepository.findById(id)
                .map(progressMapper::toDomain);
    }

    @Override
    public List<ProgressDomain> findAll() {
        return progressRepository.findAll().stream()
                .map(progressMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        progressRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return progressRepository.existsById(id);
    }

    @Override
    public long count() {
        return progressRepository.count();
    }
}
