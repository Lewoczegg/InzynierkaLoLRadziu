package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SolutionsRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SolutionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.AssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.LessonEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.SolutionMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.AssignmentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class SolutionsRepositoryAdapterMongoDB implements SolutionsRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final SolutionMapperMongoDB solutionMapper;
    private final CourseMapperMongoDB courseMapper;
    private final LessonMapperMongoDB lessonMapper;
    private final AssignmentMapperMongoDB assignmentMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public SolutionsRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository,
                                            SolutionMapperMongoDB solutionMapper,
                                            CourseMapperMongoDB courseMapper,
                                            LessonMapperMongoDB lessonMapper,
                                            AssignmentMapperMongoDB assignmentMapper,
                                            SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.solutionMapper = solutionMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
        this.assignmentMapper = assignmentMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public List<SolutionsDomain> findByAssignment_AssignmentId(Long assignmentId) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .filter(assignment -> assignment.getAssignmentId().equals(assignmentId))
                                        .flatMap(assignmentEmb -> {
                                            AssignmentDomain assignment = assignmentMapper.toDomain(assignmentEmb, lesson);
                                            return assignmentEmb.getSolutions().stream()
                                                    .map(solution -> solutionMapper.toDomain(solution, assignment));
                                        });
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public SolutionsDomain save(SolutionsDomain solution) {
        Long assignmentId = solution.getAssignment().getAssignmentId();
        
        CourseDocument courseDoc = courseRepository.findAll().stream()
                .filter(c -> c.getLessons().stream()
                        .flatMap(l -> l.getAssignments().stream())
                        .anyMatch(a -> a.getAssignmentId().equals(assignmentId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Assignment not found: " + assignmentId));
        
        courseDoc.getLessons().forEach(lesson -> {
            lesson.getAssignments().forEach(assignment -> {
                if (assignment.getAssignmentId().equals(assignmentId)) {
                    List<SolutionEmbedded> solutions = assignment.getSolutions();
                    
                    // Initialize list if null
                    if (solutions == null) {
                        solutions = new java.util.ArrayList<>();
                        assignment.setSolutions(solutions);
                    }
                    
                    // Generate ID if new solution
                    if (solution.getSolutionId() == null) {
                        solution.setSolutionId(sequenceGeneratorService.generateSequence("solution_sequence"));
                    }
                    
                    boolean found = false;
                    for (int i = 0; i < solutions.size(); i++) {
                        if (solutions.get(i).getSolutionId() != null && 
                            solutions.get(i).getSolutionId().equals(solution.getSolutionId())) {
                            solutions.set(i, solutionMapper.toEmbedded(solution));
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        solutions.add(solutionMapper.toEmbedded(solution));
                    }
                }
            });
        });
        
        courseRepository.save(courseDoc);
        return solution;
    }

    @Override
    public Optional<SolutionsDomain> findById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .flatMap(assignmentEmb -> {
                                            AssignmentDomain assignment = assignmentMapper.toDomain(assignmentEmb, lesson);
                                            return assignmentEmb.getSolutions().stream()
                                                    .filter(solution -> solution.getSolutionId().equals(id))
                                                    .map(solution -> solutionMapper.toDomain(solution, assignment));
                                        });
                            });
                })
                .findFirst();
    }

    @Override
    public List<SolutionsDomain> findAll() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .flatMap(assignmentEmb -> {
                                            AssignmentDomain assignment = assignmentMapper.toDomain(assignmentEmb, lesson);
                                            return assignmentEmb.getSolutions().stream()
                                                    .map(solution -> solutionMapper.toDomain(solution, assignment));
                                        });
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.findAll().forEach(courseDoc -> {
            courseDoc.getLessons().forEach(lesson -> {
                lesson.getAssignments().forEach(assignment -> {
                    assignment.getSolutions().removeIf(solution -> 
                            solution.getSolutionId().equals(id));
                });
            });
            courseRepository.save(courseDoc);
        });
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getAssignments().stream())
                .flatMap(assignment -> assignment.getSolutions().stream())
                .anyMatch(solution -> solution.getSolutionId().equals(id));
    }

    @Override
    public long count() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getAssignments().stream())
                .mapToLong(assignment -> assignment.getSolutions().size())
                .sum();
    }
}
