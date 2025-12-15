package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CourseRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class CourseRepositoryAdapterMongoDB implements CourseRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final CourseMapperMongoDB courseMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public CourseRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository, CourseMapperMongoDB courseMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<CourseDomain> findByTitleLvl(Title title) {
        return courseRepository.findByTitleLevel(title).stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDomain> findByTitleLvlIn(Collection<Title> titles) {
        return courseRepository.findByTitleLevelIn(titles).stream()
                .map(courseMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDomain save(CourseDomain course) {
        var courseDoc = courseMapper.toDocument(course);
        if (courseDoc.getId() == null) {
            courseDoc.setId(sequenceGeneratorService.generateSequence("courses"));
        }
        return courseMapper.toDomain(courseRepository.save(courseDoc));
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
