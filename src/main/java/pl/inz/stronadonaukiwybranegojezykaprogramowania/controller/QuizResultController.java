package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.QuizRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.QuizResult;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.QuizResultService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/QuizResult")
public class QuizResultController {

    private final QuizResultService quizResultService;

    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResult> markQuizAsCompleted(@RequestBody QuizRequest quizRequest) {
        try {
            QuizResult quizResult = quizResultService.markQuizAsCompleted(quizRequest.getQuizId(), quizRequest.getStartTime(), quizRequest.getUserAnswer());
            return ResponseEntity.ok(quizResult);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
