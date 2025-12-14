package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SubmissionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SubmissionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepositoryAdapter submissionRepositoryAdapter;

    private final UserRepositoryAdapter userRepositoryAdapter;

    public SubmissionService(SubmissionRepositoryAdapter submissionRepositoryAdapter, UserRepositoryAdapter userRepositoryAdapter) {
        this.submissionRepositoryAdapter = submissionRepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public Map<String, Object> findLatestSubmission(Long taskId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        Long userId = userRepositoryAdapter.findByUsername(username).getUserId();
        Optional<SubmissionDomain> latestSubmission = submissionRepositoryAdapter.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(userId, taskId);
        Map<String, Object> response = new HashMap<>();
        response.put("grade", latestSubmission.get().getGrade());
        response.put("content", latestSubmission.get().getContent());

        return response;
    }
}
