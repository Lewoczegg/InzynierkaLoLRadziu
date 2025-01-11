package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final CourseRepository courseRepository;

    public ProgressService(ProgressRepository progressRepository, UserRepository userRepository, LessonRepository lessonRepository, AssignmentRepository assignmentRepository, SubmissionRepository submissionRepository, CourseRepository courseRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.courseRepository = courseRepository;
    }

    public void markLessonAsCompleted(Long lessonId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        Course course = lesson.getCourse();

        Optional<Progress> existingProgress = progressRepository.findByUserUserIdAndLessonLessonId(user.getUserId(), lesson.getLessonId());
        if (existingProgress.isPresent()) {
            return;
        }

        List<Assignment> assignments = assignmentRepository.findByLessonLessonId(lesson.getLessonId());
        for (Assignment assignment : assignments) {
            Optional<Submission> submission = submissionRepository.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(
                    user.getUserId(), assignment.getAssignmentId());
            if (submission.isEmpty()) {
                return;
            }
        }
        Progress progress = new Progress();
        progress.setUser(user);
        progress.setCourse(course);
        progress.setLesson(lesson);
        progress.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        progressRepository.save(progress);
        user.setLevel(user.getLevel() + 1);
        userRepository.save(user);

        long completedLessonsCount = progressRepository.countByUserUserId(user.getUserId());
        if (user.getLevel() >= 7) {
            user.setTitle(Title.ADVANCED);
        } else if (completedLessonsCount >= 3) {
            user.setTitle(Title.INTERMEDIATE);
        }
        userRepository.save(user);
    }

    public double calculateAssignmentCompletionPercentage() {
        // Pobierz aktualnie zalogowanego użytkownika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Licz całkowitą liczbę wszystkich zadań w systemie
        long totalAssignments = assignmentRepository.count();

        // Licz liczbę unikalnych zadań, dla których użytkownik wykonał przynajmniej jedno przesłanie (Submission)
        long completedAssignments = submissionRepository.countDistinctAssignmentsByUserUserId(user.getUserId());

        if (totalAssignments == 0) {
            return 0.0;
        }

        double percentage = (double) completedAssignments / totalAssignments * 100;
        return Math.round(percentage);
    }
    public double calculateLessonCompletionPercentage() {
        // Pobierz aktualnie zalogowanego użytkownika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Licz całkowitą liczbę wszystkich lekcji w systemie
        long totalLessons = lessonRepository.count();

        // Licz liczbę lekcji ukończonych przez użytkownika (w Progress z niepustym completedAt)
        long completedLessons = progressRepository.countByUser_UserIdAndCompletedAtIsNotNull(user.getUserId());

        if (totalLessons == 0) {
            return 0.0;
        }

        double percentage = (double) completedLessons / totalLessons * 100;
        return Math.round(percentage);
    }

    public double calculateCourseCompletionPercentage() {
        // Pobierz aktualnie zalogowanego użytkownika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Pobierz wszystkie kursy
        List<Course> allCourses = courseRepository.findAll();

        // Zmienna do zliczania ukończonych kursów
        long completedCoursesCount = 0;

        // Iteruj przez każdy kurs
        for (Course course : allCourses) {
            // Liczba wszystkich lekcji w danym kursie
            long totalLessonsInCourse = lessonRepository.countByCourse_CourseId(course.getCourseId());

            // Liczba lekcji ukończonych przez użytkownika w danym kursie
            long completedLessonsInCourse = progressRepository.countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(user.getUserId(), course.getCourseId());

            // Jeśli wszystkie lekcje są ukończone, kurs jest uznany za ukończony
            if (totalLessonsInCourse > 0 && totalLessonsInCourse == completedLessonsInCourse) {
                completedCoursesCount++;
            }
        }

        // Oblicz procent ukończonych kursów
        long totalCourses = allCourses.size();
        if (totalCourses == 0) {
            return 0.0;
        }

        double percentage = (double) completedCoursesCount / totalCourses * 100;
        return Math.round(percentage);
    }
}

