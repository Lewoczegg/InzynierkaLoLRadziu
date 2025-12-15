package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.LessonRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.LessonEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class LessonRepositoryAdapterMongoDB implements LessonRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final LessonMapperMongoDB lessonMapper;
    private final CourseMapperMongoDB courseMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public LessonRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository, 
                                         LessonMapperMongoDB lessonMapper,
                                         CourseMapperMongoDB courseMapper,
                                         SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
        this.courseMapper = courseMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<LessonDomain> findByCourseCourseId(Long courseId) {
        return courseRepository.findById(courseId)
                .map(courseDoc -> {
                    var course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .map(lessonEmb -> lessonMapper.toDomain(lessonEmb, course))
                            .collect(Collectors.toList());
                })
                .orElse(new ArrayList<>());
    }

    @Override
    public long countByCourse_CourseId(Long courseId) {
        return courseRepository.findById(courseId)
                .map(courseDoc -> (long) courseDoc.getLessons().size())
                .orElse(0L);
    }

    @Override
    public LessonDomain save(LessonDomain lesson) {
        // Find the course containing this lesson
        Long courseId = lesson.getCourse().getCourseId();
        Optional<CourseDocument> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new RuntimeException("Course not found: " + courseId);
        }
        CourseDocument courseDoc = courseOpt.get();
        List<LessonEmbedded> lessons = courseDoc.getLessons();
        
        // Initialize list if null
        if (lessons == null) {
            lessons = new java.util.ArrayList<>();
            courseDoc.setLessons(lessons);
        }

        // Assign a new id if this is a new lesson (id is null)
        if (lesson.getLessonId() == null) {
            lesson.setLessonId(sequenceGeneratorService.generateSequence("lesson_sequence"));
        }

        // Find and update or add new lesson
        boolean found = false;
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getLessonId() != null && 
                lessons.get(i).getLessonId().equals(lesson.getLessonId())) {
                lessons.set(i, lessonMapper.toEmbedded(lesson));
                found = true;
                break;
            }
        }
        if (!found) {
            lessons.add(lessonMapper.toEmbedded(lesson));
        }
        courseRepository.save(courseDoc);
        return lesson;
    }

    @Override
    public Optional<LessonDomain> findById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    var course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .filter(lessonEmb -> lessonEmb.getLessonId().equals(id))
                            .map(lessonEmb -> lessonMapper.toDomain(lessonEmb, course));
                })
                .findFirst();
    }

    @Override
    public List<LessonDomain> findAll() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    var course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .map(lessonEmb -> lessonMapper.toDomain(lessonEmb, course));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.findAll().forEach(courseDoc -> {
            courseDoc.getLessons().removeIf(lesson -> lesson.getLessonId().equals(id));
            courseRepository.save(courseDoc);
        });
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .anyMatch(lessonEmb -> lessonEmb.getLessonId().equals(id));
    }

    @Override
    public long count() {
        return courseRepository.findAll().stream()
                .mapToLong(courseDoc -> courseDoc.getLessons().size())
                .sum();
    }
}
