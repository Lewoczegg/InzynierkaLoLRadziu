package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SubmissionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SubmissionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.AssignmentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.LessonEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.SubmissionMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.AssignmentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class SubmissionRepositoryAdapterMongoDB implements SubmissionRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final UserRepositoryMongoDB userRepository;
    private final SubmissionMapperMongoDB submissionMapper;
    private final CourseMapperMongoDB courseMapper;
    private final LessonMapperMongoDB lessonMapper;
    private final AssignmentMapperMongoDB assignmentMapper;
    private final UserMapperMongoDB userMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public SubmissionRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository,
                                             UserRepositoryMongoDB userRepository,
                                             SubmissionMapperMongoDB submissionMapper,
                                             CourseMapperMongoDB courseMapper,
                                             LessonMapperMongoDB lessonMapper,
                                             AssignmentMapperMongoDB assignmentMapper,
                                             UserMapperMongoDB userMapper,
                                             SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.submissionMapper = submissionMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
        this.assignmentMapper = assignmentMapper;
        this.userMapper = userMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<SubmissionDomain> findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(Long userId, Long assignmentId) {
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
                                            return assignmentEmb.getSubmissions().stream()
                                                    .filter(submission -> submission.getUserId() != null && submission.getUserId().equals(userId))
                                                    .sorted((s1, s2) -> s2.getSubmittedAt().compareTo(s1.getSubmittedAt()))
                                                    .findFirst()
                                                    .flatMap(submissionEmb -> userRepository.findById(submissionEmb.getUserId())
                                                            .map(userDoc -> {
                                                                UserDomain user = userMapper.toDomain(userDoc);
                                                                return submissionMapper.toDomain(submissionEmb, assignment, user);
                                                            })).stream();
                                        });
                            });
                })
                .findFirst();
    }

    @Override
    public long countDistinctAssignmentsByUserUserId(Long userId) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getAssignments().stream())
                .filter(assignment -> assignment.getSubmissions().stream()
                        .anyMatch(submission -> submission.getUserId() != null && submission.getUserId().equals(userId)))
                .map(assignment -> assignment.getAssignmentId())
                .distinct()
                .count();
    }

    @Override
    public SubmissionDomain save(SubmissionDomain submission) {
        Long assignmentId = submission.getAssignment().getAssignmentId();
        
        CourseDocument courseDoc = courseRepository.findAll().stream()
                .filter(c -> c.getLessons().stream()
                        .flatMap(l -> l.getAssignments().stream())
                        .anyMatch(a -> a.getAssignmentId().equals(assignmentId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Assignment not found: " + assignmentId));
        
        courseDoc.getLessons().forEach(lesson -> {
            lesson.getAssignments().forEach(assignment -> {
                if (assignment.getAssignmentId().equals(assignmentId)) {
                    List<SubmissionEmbedded> submissions = assignment.getSubmissions();
                    
                    // Initialize list if null
                    if (submissions == null) {
                        submissions = new java.util.ArrayList<>();
                        assignment.setSubmissions(submissions);
                    }
                    
                    // Generate ID if new submission
                    if (submission.getSubmissionId() == null) {
                        submission.setSubmissionId(sequenceGeneratorService.generateSequence("submission_sequence"));
                    }
                    
                    boolean found = false;
                    for (int i = 0; i < submissions.size(); i++) {
                        if (submissions.get(i).getSubmissionId() != null && 
                            submissions.get(i).getSubmissionId().equals(submission.getSubmissionId())) {
                            submissions.set(i, submissionMapper.toEmbedded(submission));
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        submissions.add(submissionMapper.toEmbedded(submission));
                    }
                }
            });
        });
        
        courseRepository.save(courseDoc);
        return submission;
    }

    @Override
    public Optional<SubmissionDomain> findById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .flatMap(assignmentEmb -> {
                                            AssignmentDomain assignment = assignmentMapper.toDomain(assignmentEmb, lesson);
                                            return assignmentEmb.getSubmissions().stream()
                                                    .filter(submission -> submission.getSubmissionId().equals(id))
                                                    .flatMap(submissionEmb -> userRepository.findById(submissionEmb.getUserId())
                                                            .map(userDoc -> {
                                                                UserDomain user = userMapper.toDomain(userDoc);
                                                                return submissionMapper.toDomain(submissionEmb, assignment, user);
                                                            }).stream());
                                        });
                            });
                })
                .findFirst();
    }

    @Override
    public List<SubmissionDomain> findAll() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getAssignments().stream()
                                        .flatMap(assignmentEmb -> {
                                            AssignmentDomain assignment = assignmentMapper.toDomain(assignmentEmb, lesson);
                                            return assignmentEmb.getSubmissions().stream()
                                                    .flatMap(submissionEmb -> userRepository.findById(submissionEmb.getUserId())
                                                            .map(userDoc -> {
                                                                UserDomain user = userMapper.toDomain(userDoc);
                                                                return submissionMapper.toDomain(submissionEmb, assignment, user);
                                                            }).stream());
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
                    assignment.getSubmissions().removeIf(submission -> 
                            submission.getSubmissionId().equals(id));
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
                .flatMap(assignment -> assignment.getSubmissions().stream())
                .anyMatch(submission -> submission.getSubmissionId().equals(id));
    }

    @Override
    public long count() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getAssignments().stream())
                .mapToLong(assignment -> assignment.getSubmissions().size())
                .sum();
    }
}
