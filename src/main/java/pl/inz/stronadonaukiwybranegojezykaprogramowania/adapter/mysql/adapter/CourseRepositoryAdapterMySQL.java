package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CourseRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.CourseMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.CourseRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourseRepositoryAdapterMySQL implements CourseRepositoryAdapter {

    private final CourseRepositoryMySQL courseRepository;
    private final CourseMapperMySQL courseMapper;

    public CourseRepositoryAdapterMySQL(CourseRepositoryMySQL courseRepository, CourseMapperMySQL courseMapper) {
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
