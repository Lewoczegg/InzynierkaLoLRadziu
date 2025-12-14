package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.exception.ResourceNotFoundException;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.AssignmentDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.SolutionsDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.AssignmentRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.SolutionsRepositoryAdapter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SolutionsService {

    @Autowired
    private SolutionsRepositoryAdapter solutionRepositoryAdapter;
    @Autowired
    private AssignmentRepositoryAdapter assignmentRepositoryAdapter;

    public SolutionsDomain createSolution(Long assignmentId, String content, String language) {
        Optional<AssignmentDomain> solutionsOpt = assignmentRepositoryAdapter.findById(assignmentId);
        if (solutionsOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        SolutionsDomain solution = new SolutionsDomain();
        solution.setLanguage(language);
        solution.setContent(content);
        solution.setAssignment(solutionsOpt.get());
        solution.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return solutionRepositoryAdapter.save(solution);
    }

    public List<SolutionsDomain> getAllSolutions() {
        return solutionRepositoryAdapter.findAll();
    }

    public Optional<SolutionsDomain> getSolutionById(Long solutionId) {
        return solutionRepositoryAdapter.findById(solutionId);
    }

    public List<SolutionsDomain> getSolutionsByAssignmentId(Long assignmentId) {
        return solutionRepositoryAdapter.findByAssignment_AssignmentId(assignmentId);
    }

    public SolutionsDomain updateSolution(Long solutionId, SolutionsDomain solutionDetails) {
        return solutionRepositoryAdapter.findById(solutionId).map(solution -> {
            solution.setContent(solutionDetails.getContent());
            solution.setLanguage(solutionDetails.getLanguage());
            return solutionRepositoryAdapter.save(solution);
        }).orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));
    }

    public void deleteSolution(Long solutionId) {
        SolutionsDomain solution = solutionRepositoryAdapter.findById(solutionId)
                .orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));
        solutionRepositoryAdapter.deleteById(solution.getSolutionId());
    }
}
