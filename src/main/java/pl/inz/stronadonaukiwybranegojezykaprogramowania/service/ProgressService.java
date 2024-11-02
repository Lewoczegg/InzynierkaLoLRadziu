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
    private final SolutionsRepository solutionsRepository;
    private final SubmissionRepository submissionRepository;

    public ProgressService(ProgressRepository progressRepository, UserRepository userRepository, LessonRepository lessonRepository, AssignmentRepository assignmentRepository, SolutionsRepository solutionsRepository, SubmissionRepository submissionRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.assignmentRepository = assignmentRepository;
        this.solutionsRepository = solutionsRepository;
        this.submissionRepository = submissionRepository;
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

        userRepository.save(user);
        Progress progress = new Progress();
        progress.setUser(user);
        progress.setCourse(course);
        progress.setLesson(lesson);
        progress.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        progressRepository.save(progress);
        user.setLevel(user.getLevel() + 1);

        long completedLessonsCount = progressRepository.countByUserUserId(user.getUserId());
        if (completedLessonsCount >= 3 && user.getLevel() < 7) {
            user.setTitle(Title.INTERMEDIATE);
        }

        if (user.getLevel() >= 7) {
            user.setTitle(Title.ADVANCED);
        }
    }
}

