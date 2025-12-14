package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepositoryAdapter progressRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final LessonRepositoryAdapter lessonRepositoryAdapter;
    private final AssignmentRepositoryAdapter assignmentRepositoryAdapter;
    private final SubmissionRepositoryAdapter submissionRepositoryAdapter;
    private final CourseRepositoryAdapter courseRepositoryAdapter;

    public ProgressService(ProgressRepositoryAdapter progressRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter, LessonRepositoryAdapter lessonRepositoryAdapter, AssignmentRepositoryAdapter assignmentRepositoryAdapter, SubmissionRepositoryAdapter submissionRepositoryAdapter, CourseRepositoryAdapter courseRepositoryAdapter) {
        this.progressRepositoryAdapter = progressRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.lessonRepositoryAdapter = lessonRepositoryAdapter;
        this.assignmentRepositoryAdapter = assignmentRepositoryAdapter;
        this.submissionRepositoryAdapter = submissionRepositoryAdapter;
        this.courseRepositoryAdapter = courseRepositoryAdapter;
    }

    public void markLessonAsCompleted(Long lessonId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        LessonDomain lesson = lessonRepositoryAdapter.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        CourseDomain course = lesson.getCourse();

        Optional<ProgressDomain> existingProgress = progressRepositoryAdapter.findByUserUserIdAndLessonLessonId(user.getUserId(), lesson.getLessonId());
        if (existingProgress.isPresent()) {
            return;
        }

        List<AssignmentDomain> assignments = assignmentRepositoryAdapter.findByLessonLessonId(lesson.getLessonId());
        for (AssignmentDomain assignment : assignments) {
            Optional<SubmissionDomain> submission = submissionRepositoryAdapter.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(
                    user.getUserId(), assignment.getAssignmentId());
            if (submission.isEmpty()) {
                return;
            }
        }
        ProgressDomain progress = new ProgressDomain();
        progress.setUser(user);
        progress.setCourse(course);
        progress.setLesson(lesson);
        progress.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        progressRepositoryAdapter.save(progress);
        user.setLevel(user.getLevel() + 1);
        userRepositoryAdapter.save(user);

        long completedLessonsCount = progressRepositoryAdapter.countByUserUserId(user.getUserId());
        if (user.getLevel() >= 7) {
            user.setTitle(Title.ADVANCED);
        } else if (completedLessonsCount >= 3) {
            user.setTitle(Title.INTERMEDIATE);
        }
        userRepositoryAdapter.save(user);
    }

    public double calculateAssignmentCompletionPercentage() {
        // Pobierz aktualnie zalogowanego użytkownika
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Licz całkowitą liczbę wszystkich zadań w systemie
        long totalAssignments = assignmentRepositoryAdapter.count();

        // Licz liczbę unikalnych zadań, dla których użytkownik wykonał przynajmniej jedno przesłanie (Submission)
        long completedAssignments = submissionRepositoryAdapter.countDistinctAssignmentsByUserUserId(user.getUserId());

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
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Licz całkowitą liczbę wszystkich lekcji w systemie
        long totalLessons = lessonRepositoryAdapter.count();

        // Licz liczbę lekcji ukończonych przez użytkownika (w Progress z niepustym completedAt)
        long completedLessons = progressRepositoryAdapter.countByUser_UserIdAndCompletedAtIsNotNull(user.getUserId());

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
        UserDomain user = userRepositoryAdapter.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Pobierz wszystkie kursy
        List<CourseDomain> allCourses = courseRepositoryAdapter.findAll();

        // Zmienna do zliczania ukończonych kursów
        long completedCoursesCount = 0;

        // Iteruj przez każdy kurs
        for (CourseDomain course : allCourses) {
            // Liczba wszystkich lekcji w danym kursie
            long totalLessonsInCourse = lessonRepositoryAdapter.countByCourse_CourseId(course.getCourseId());

            // Liczba lekcji ukończonych przez użytkownika w danym kursie
            long completedLessonsInCourse = progressRepositoryAdapter.countByUser_UserIdAndCourse_CourseIdAndCompletedAtIsNotNull(user.getUserId(), course.getCourseId());

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

