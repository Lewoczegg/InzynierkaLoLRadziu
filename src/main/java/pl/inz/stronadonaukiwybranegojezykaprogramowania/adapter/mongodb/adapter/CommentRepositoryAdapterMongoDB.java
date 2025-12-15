package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.CommentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CommentEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CommentMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.CourseMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.LessonMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.UserMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.CourseRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.UserRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CommentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.LessonDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class CommentRepositoryAdapterMongoDB implements CommentRepositoryAdapter {

    private final CourseRepositoryMongoDB courseRepository;
    private final UserRepositoryMongoDB userRepository;
    private final CommentMapperMongoDB commentMapper;
    private final LessonMapperMongoDB lessonMapper;
    private final CourseMapperMongoDB courseMapper;
    private final UserMapperMongoDB userMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public CommentRepositoryAdapterMongoDB(CourseRepositoryMongoDB courseRepository,
                                          UserRepositoryMongoDB userRepository,
                                          CommentMapperMongoDB commentMapper,
                                          LessonMapperMongoDB lessonMapper,
                                          CourseMapperMongoDB courseMapper,
                                          UserMapperMongoDB userMapper,
                                          SequenceGeneratorService sequenceGeneratorService) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
        this.lessonMapper = lessonMapper;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public CommentDomain save(CommentDomain comment) {
        Long lessonId = comment.getLesson().getLessonId();
        
        CourseDocument courseDoc = courseRepository.findAll().stream()
                .filter(c -> c.getLessons().stream()
                        .anyMatch(l -> l.getLessonId().equals(lessonId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Lesson not found: " + lessonId));
        
        courseDoc.getLessons().forEach(lesson -> {
            if (lesson.getLessonId().equals(lessonId)) {
                List<CommentEmbedded> comments = lesson.getComments();
                
                // Initialize list if null
                if (comments == null) {
                    comments = new java.util.ArrayList<>();
                    lesson.setComments(comments);
                }
                
                // Generate ID if new comment
                if (comment.getCommentId() == null) {
                    comment.setCommentId(sequenceGeneratorService.generateSequence("comment_sequence"));
                }
                
                boolean found = false;
                for (int i = 0; i < comments.size(); i++) {
                    if (comments.get(i).getCommentId() != null && 
                        comments.get(i).getCommentId().equals(comment.getCommentId())) {
                        comments.set(i, commentMapper.toEmbedded(comment));
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    comments.add(commentMapper.toEmbedded(comment));
                }
            }
        });
        
        courseRepository.save(courseDoc);
        return comment;
    }

    @Override
    public Optional<CommentDomain> findById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getComments().stream()
                                        .filter(commentEmb -> commentEmb.getCommentId().equals(id))
                                        .map(commentEmb -> {
                                            UserDomain user = userRepository.findById(commentEmb.getUserId())
                                                    .map(userDoc -> userMapper.toDomain(userDoc))
                                                    .orElse(null);
                                            return commentMapper.toDomain(commentEmb, lesson, user);
                                        });
                            });
                })
                .findFirst();
    }

    @Override
    public List<CommentDomain> findAll() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> {
                    CourseDomain course = courseMapper.toDomain(courseDoc);
                    return courseDoc.getLessons().stream()
                            .flatMap(lessonEmb -> {
                                LessonDomain lesson = lessonMapper.toDomain(lessonEmb, course);
                                return lessonEmb.getComments().stream()
                                        .map(commentEmb -> {
                                            UserDomain user = userRepository.findById(commentEmb.getUserId())
                                                    .map(userDoc -> userMapper.toDomain(userDoc))
                                                    .orElse(null);
                                            return commentMapper.toDomain(commentEmb, lesson, user);
                                        });
                            });
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.findAll().forEach(courseDoc -> {
            courseDoc.getLessons().forEach(lesson -> {
                lesson.getComments().removeIf(comment -> 
                        comment.getCommentId().equals(id));
            });
            courseRepository.save(courseDoc);
        });
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .flatMap(lesson -> lesson.getComments().stream())
                .anyMatch(comment -> comment.getCommentId().equals(id));
    }

    @Override
    public long count() {
        return courseRepository.findAll().stream()
                .flatMap(courseDoc -> courseDoc.getLessons().stream())
                .mapToLong(lesson -> lesson.getComments().size())
                .sum();
    }
}
