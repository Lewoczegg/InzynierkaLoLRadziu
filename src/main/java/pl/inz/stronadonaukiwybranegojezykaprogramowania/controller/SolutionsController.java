package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.AssignmentAddRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.SolutionsAddRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.exception.ResourceNotFoundException;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Solutions;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.SolutionsRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.SolutionsService;

import java.util.List;

@RestController
@RequestMapping("/solutions")
public class SolutionsController {
    @Autowired
    private SolutionsService solutionService;

    @PostMapping("/add")
    public Solutions createSolution(@RequestBody SolutionsAddRequest solutionsAddRequest) {
        return solutionService.createSolution(solutionsAddRequest.getAssignmentId(), solutionsAddRequest.getContent(), solutionsAddRequest.getLanguage());
    }

    @GetMapping
    public List<Solutions> getAllSolutions() {
        return solutionService.getAllSolutions();
    }

    @GetMapping("/{solutionId}")
    public ResponseEntity<Solutions> getSolutionById(@PathVariable Long solutionId) {
        return solutionService.getSolutionById(solutionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Solutions> getSolutionsByAssignmentId(@PathVariable Long assignmentId) {
        return solutionService.getSolutionsByAssignmentId(assignmentId);
    }

    @PutMapping("/{solutionId}")
    public ResponseEntity<Solutions> updateSolution(@PathVariable Long solutionId, @RequestBody Solutions solutionDetails) {
        try {
            Solutions updatedSolution = solutionService.updateSolution(solutionId, solutionDetails);
            return ResponseEntity.ok(updatedSolution);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{solutionId}")
    public ResponseEntity<Void> deleteSolution(@PathVariable Long solutionId) {
        try {
            solutionService.deleteSolution(solutionId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


