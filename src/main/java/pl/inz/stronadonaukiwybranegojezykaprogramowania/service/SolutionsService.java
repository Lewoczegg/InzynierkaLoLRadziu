package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.exception.ResourceNotFoundException;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Solutions;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.AssignmentRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.SolutionsRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SolutionsService {

    @Autowired
    private SolutionsRepository solutionRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Solutions createSolution(Long assignmentId, String content, String language) {
        Optional<Assignment> solutionsOpt = assignmentRepository.findById(assignmentId);
        if (solutionsOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Solutions solution = new Solutions();
        solution.setLanguage(language);
        solution.setContent(content);
        solution.setAssignment(solutionsOpt.get());
        solution.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return solutionRepository.save(solution);
    }

    public List<Solutions> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Optional<Solutions> getSolutionById(Long solutionId) {
        return solutionRepository.findById(solutionId);
    }

    public List<Solutions> getSolutionsByAssignmentId(Long assignmentId) {
        return solutionRepository.findByAssignment_AssignmentId(assignmentId);
    }

    public Solutions updateSolution(Long solutionId, Solutions solutionDetails) {
        return solutionRepository.findById(solutionId).map(solution -> {
            solution.setContent(solutionDetails.getContent());
            solution.setLanguage(solutionDetails.getLanguage());
            return solutionRepository.save(solution);
        }).orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));
    }

    public void deleteSolution(Long solutionId) {
        Solutions solution = solutionRepository.findById(solutionId)
                .orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));
        solutionRepository.delete(solution);
    }
}
