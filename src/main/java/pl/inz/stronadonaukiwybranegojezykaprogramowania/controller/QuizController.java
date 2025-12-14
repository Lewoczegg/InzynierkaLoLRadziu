package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.MessageResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.QuizService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @GetMapping("/daily")
    public ResponseEntity<?> getDailyQuiz() {
        Optional<QuizDomain> quiz = quizService.getDailyQuizForUser();
        if (quiz.isPresent()) {
            QuizDomain quizToReturn = quiz.get();
            quizToReturn.getQuestions().forEach(question -> question.setCorrectAnswer(null));
            return ResponseEntity.ok(quizToReturn);
        } else {
            return ResponseEntity.ok(new MessageResponse("done"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<QuizDomain> createQuiz(@RequestBody QuizDomain quiz) {
        QuizDomain savedQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(savedQuiz);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDomain> getQuizById(@PathVariable Long quizId) {
        QuizDomain quiz = quizService.getQuizById(quizId);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizDomain>> getAllQuizzes() {
        List<QuizDomain> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDomain> updateQuiz(@PathVariable Long quizId, @RequestBody QuizDomain updatedQuiz) {
        QuizDomain savedQuiz = quizService.updateQuiz(quizId, updatedQuiz);
        return ResponseEntity.ok(savedQuiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
}