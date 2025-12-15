package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.AssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.AssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.AssignmentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class AssignmentRepositoryAdapterMongoDB implements AssignmentRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final AssignmentMapperMongoDB assignmentMapper;
    private final LessonMapperMongoDB lessonMapper;
    private final CourseMapperMongoDB courseMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public AssignmentRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository, 
                                             AssignmentMapperMongoDB assignmentMapper,
                                             LessonMapperMongoDB lessonMapper,
                                             CourseMapperMongoDB courseMapper,
                                             SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.assignmentMapper = assignmentMapper;
        this.lessonMapper = lessonMapper;
        this.courseMapper = courseMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public AssignmentDomain findByAssignmentId(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .filter(assignment -> assignment.getAssignmentId().equals(id))
                                        .map(assignment -> assignmentMapper.toDomain(assignment, lesson));
                            });
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AssignmentDomain> findByLessonLessonId(Long lessonId) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .filter(lessonEmb -> lessonEmb.getLessonId().equals(lessonId))
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .map(assignment -> assignmentMapper.toDomain(assignment, lesson));
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDomain save(AssignmentDomain assignment) {
        Long lessonId = assignment.getLesson().getLessonId();
        
        CourseDocument courseDoc = courseRepository.findAll().stream()
                .filter(c -> c.getLessons().stream()
                        .anyMatch(l -> l.getLessonId().equals(lessonId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Lesson not found: " + lessonId));
        
        courseDoc.getLessons().forEach(lesson -> {
            if (lesson.getLessonId().equals(lessonId)) {
                List<AssignmentEmbedded> assignments = lesson.getAssignments();
                
                // Initialize list if null
                if (assignments == null) {
                    assignments = new java.util.ArrayList<>();
                    lesson.setAssignments(assignments);
                }
                
                // Generate ID if new assignment
                if (assignment.getAssignmentId() == null) {
                    assignment.setAssignmentId(sequenceGeneratorService.generateSequence("assignment_sequence"));
                }
                
                boolean found = false;
                for (int i = 0; i < assignments.size(); i++) {
                    if (assignments.get(i).getAssignmentId() != null && 
                        assignments.get(i).getAssignmentId().equals(assignment.getAssignmentId())) {
                        assignments.set(i, assignmentMapper.toEmbedded(assignment));
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    assignments.add(assignmentMapper.toEmbedded(assignment));
                }
            }
        });
        
        courseRepository.save(courseDoc);
        return assignment;
    }

    @Override
    public Optional<AssignmentDomain> findById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .filter(assignment -> assignment.getAssignmentId().equals(id))
                                        .map(assignment -> assignmentMapper.toDomain(assignment, lesson));
                            });
                })
                .findFirst();
    }

    @Override
    public List<AssignmentDomain> findAll() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .map(assignment -> assignmentMapper.toDomain(assignment, lesson));
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.findAll().forEach(courseDoc -> {
            courseDoc.getLessons().forEach(lesson -> {
                lesson.getAssignments().removeIf(assignment -> 
                        assignment.getAssignmentId().equals(id));
            });
            courseRepository.save(courseDoc);
        });
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getAssignments().stream())
                .anyMatch(assignment -> assignment.getAssignmentId().equals(id));
    }

    @Override
    public long count() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .mapToLong(lesson -> lesson.getAssignments().size())
                .sum();
    }
}
