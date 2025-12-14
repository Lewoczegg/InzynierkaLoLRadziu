package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CourseRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.CourseMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.CourseRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class CourseRepositoryAdapterPostgres implements CourseRepositoryAdapter {

    private final CourseRepositoryPostgres courseRepository;
    private final CourseMapperPostgres courseMapper;

    public CourseRepositoryAdapterPostgres(CourseRepositoryPostgres courseRepository, CourseMapperPostgres courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDomain> findByTitleLvl(Title title) {
        return courseRepository.findByTitleLvl(title).stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDomain> findByTitleLvlIn(Collection<Title> titles) {
        return courseRepository.findByTitleLvlIn(titles).stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDomain save(CourseDomain course) {
        return courseMapper.toDomain(
                courseRepository.save(courseMapper.toEntity(course))
        );
    }

    @Override
    public Optional<CourseDomain> findById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDomain);
    }

    @Override
    public List<CourseDomain> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    @Override
    public long count() {
        return courseRepository.count();
    }
}
