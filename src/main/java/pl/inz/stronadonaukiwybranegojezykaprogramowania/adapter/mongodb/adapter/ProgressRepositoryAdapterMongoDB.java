package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.ProgressRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.ProgressEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.UserDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.ProgressMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.ProgressDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class ProgressRepositoryAdapterMongoDB implements ProgressRepositoryAdapter {

    private final UserRepositoryMongoDB userRepository;
    private final CourseRepositoryMongoDB courseRepository;
    private final ProgressMapperMongoDB progressMapper;
    private final UserMapperMongoDB userMapper;
    private final CourseMapperMongoDB courseMapper;
    private final LessonMapperMongoDB lessonMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public ProgressRepositoryAdapterMongoDB(UserRepositoryMongoDB userRepository,
                                           CourseRepositoryMongoDB courseRepository,
                                           ProgressMapperMongoDB progressMapper,
                                           UserMapperMongoDB userMapper,
                                           CourseMapperMongoDB courseMapper,
                                           LessonMapperMongoDB lessonMapper,
                                           SequenceGeneratorService sequenceGeneratorService) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.progressMapper = progressMapper;
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Optional<ProgressDomain> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId) {
        return userRepository.findById(userId)
                .flatMap(userDoc -> {
                    UserDomain user = userMapper.toDomain(userDoc);
                    if (userDoc.getProgress() == null) {
                        return Optional.empty();
                    }
                    return userDoc.getProgress().stream()
                            .filter(p -> p.getLessonId().equals(lessonId))
                            .map(p -> buildProgressDomain(p, user))
                            .findFirst();
                });
    }

    private ProgressDomain buildProgressDomain(ProgressEmbedded embedded, UserDomain user) {
        CourseDomain course = courseRepository.findById(embedded.getCourseId())
                .map(courseMapper::toDomain)
                .orElse(null);
        LessonDomain lesson = courseRepository.findById(embedded.getCourseId())
                .flatMap(courseDoc -> courseDoc.getLessons().stream()
                        .filter(l -> l.getLessonId().equals(embedded.getLessonId()))
                        .findFirst()
                        .map(l -> lessonMapper.toDomain(l, course)))
                .orElse(null);
        return progressMapper.toDomain(embedded, user, course, lesson);
    }

    @Override
    public Long countByUserUserId(Long userId) {
        return userRepository.findById(userId)
                .map(userDoc -> userDoc.getProgress() != null ? (long) userDoc.getProgress().size() : 0L)
                .orElse(0L);
    }

    @Override
    public long countByUser_UserIdAndCompletedAtIsNotNull(Long userId) {
        return userRepository.findById(userId)
                .map(userDoc -> userDoc.getProgress() != null ? userDoc.getProgress().stream()
                        .filter(p -> p.getCompletedAt() != null)
                        .count() : 0L)
                .orElse(0L);
    }

    @Override
    public long countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(Long userId, Long courseId) {
        return userRepository.findById(userId)
                .map(userDoc -> userDoc.getProgress() != null ? userDoc.getProgress().stream()
                        .filter(p -> p.getCourseId().equals(courseId))
                        .filter(p -> p.getCompletedAt() != null)
                        .count() : 0L)
                .orElse(0L);
    }

    @Override
    public ProgressDomain save(ProgressDomain progress) {
        Long userId = progress.getUser().getUserId();
        
        Optional<UserDocument> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + userId);
        }
        
        UserDocument userDoc = userOpt.get();
        List<ProgressEmbedded> progressList = userDoc.getProgress();
        
        // Initialize list if null
        if (progressList == null) {
            progressList = new java.util.ArrayList<>();
            userDoc.setProgress(progressList);
        }
        
        // Remove existing progress for the same course/lesson
        progressList.removeIf(p -> 
                p.getCourseId().equals(progress.getCourse().getCourseId()) &&
                p.getLessonId().equals(progress.getLesson().getLessonId()));
        
        // Generate ID if new progress
        if (progress.getProgressId() == null) {
            progress.setProgressId(sequenceGeneratorService.generateSequence("progress_sequence"));
        }
        
        // Add new progress
        progressList.add(progressMapper.toEmbedded(progress));
        
        userRepository.save(userDoc);
        return progress;
    }

    @Override
    public Optional<ProgressDomain> findById(Long id) {
        // Progress doesn't have a separate ID in MongoDB - identified by user/course/lesson combo
        return Optional.empty();
    }

    @Override
    public List<ProgressDomain> findAll() {
        return userRepository.findAll().stream()
                .flatMap(userDoc -> {
                    UserDomain user = userMapper.toDomain(userDoc);
                    return userDoc.getProgress().stream()
                            .map(p -> buildProgressDomain(p, user));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // Not applicable - use composite key deletion
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return userRepository.findAll().stream()
                .mapToLong(userDoc -> userDoc.getProgress().size())
                .sum();
    }
}
