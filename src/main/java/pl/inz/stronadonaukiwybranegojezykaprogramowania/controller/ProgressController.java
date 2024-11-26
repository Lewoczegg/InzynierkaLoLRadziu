package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.ProgressService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Progress")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/assignmentCompletionPercentage")
    public Map<String, Object>  getAssignmentCompletionPercentage() {
        double percentage = progressService.calculateAssignmentCompletionPercentage();

        Map<String, Object> response = new HashMap<>();
        response.put("count", percentage);

        return response;
    }

    @GetMapping("/lessonCompletionPercentage")
    public Map<String, Object> getLessonCompletionPercentage() {
        double percentage = progressService.calculateLessonCompletionPercentage();

        Map<String, Object> response = new HashMap<>();
        response.put("count", percentage);

        return response;
    }

    @GetMapping("/courseCompletionPercentage")
    public Map<String, Object> getCourseCompletionPercentage() {
        double percentage = progressService.calculateCourseCompletionPercentage();
        Map<String, Object> response = new HashMap<>();
        response.put("count", percentage);

        return response;
    }


}
