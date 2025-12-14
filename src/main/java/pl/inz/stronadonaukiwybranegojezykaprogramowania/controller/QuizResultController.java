package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.QuizRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.QuizResultService;

import java.util.Map;

@RestController
@RequestMapping("/QuizResult")
public class QuizResultController {

    private final QuizResultService quizResultService;

    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultDomain> markQuizAsCompleted(@RequestBody QuizRequest quizRequest) {
        try {
            QuizResultDomain quizResult = quizResultService.markQuizAsCompleted(quizRequest.getQuizId(), quizRequest.getStartTime(), quizRequest.getUserAnswer());
            return ResponseEntity.ok(quizResult);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/total-points")
    public ResponseEntity<Map<String, Long>> getTotalPointsForAllUsers() {
        Map<String, Long> userPointsMap = quizResultService.getTotalPointsForAllUsers();
        return ResponseEntity.ok(userPointsMap);
    }
}
