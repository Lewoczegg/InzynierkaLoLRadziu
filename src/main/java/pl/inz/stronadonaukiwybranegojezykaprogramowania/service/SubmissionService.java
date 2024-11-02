package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Submission;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.SubmissionRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submisionReposiotry;

    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submisionReposiotry, UserRepository userRepository) {
        this.submisionReposiotry = submisionReposiotry;
        this.userRepository = userRepository;
    }

    public Map<String, Object> findLatestSubmission(Long taskId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Sprawdzenie czy użytkownik jest uwierzytelniony
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        // Wyciągnięcie nazwy użytkownika z tokena
        String username = authentication.getName();
        Long userId = userRepository.findByUsername(username).getUserId();
        Optional<Submission> latestSubmission = submisionReposiotry.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(userId, taskId);
        Map<String, Object> response = new HashMap<>();
        response.put("grade", latestSubmission.get().getGrade());
        response.put("content", latestSubmission.get().getContent());

        return response;
    }
}
