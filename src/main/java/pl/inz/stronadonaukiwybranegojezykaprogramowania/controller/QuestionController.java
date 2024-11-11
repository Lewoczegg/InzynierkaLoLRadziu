package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Question;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Tworzenie nowego pytania w quizie
    @PostMapping("/create/{quizId}")
    public ResponseEntity<Question> createQuestion(@PathVariable Long quizId, @RequestBody Question question) {
        Question savedQuestion = questionService.createQuestion(quizId, question);
        return ResponseEntity.ok(savedQuestion);
    }

    // Pobieranie pytania po ID
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(question);
    }

    // Pobieranie wszystkich pytań
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Aktualizacja istniejącego pytania
    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
        Question savedQuestion = questionService.updateQuestion(questionId, updatedQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    // Usuwanie pytania
    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }
}