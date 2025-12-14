package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create/{quizId}")
    public ResponseEntity<QuestionDomain> createQuestion(@PathVariable Long quizId, @RequestBody QuestionDomain question) {
        QuestionDomain savedQuestion = questionService.createQuestion(quizId, question);
        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDomain> getQuestionById(@PathVariable Long questionId) {
        QuestionDomain question = questionService.getQuestionById(questionId);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDomain>> getAllQuestions() {
        List<QuestionDomain> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionDomain> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDomain updatedQuestion) {
        QuestionDomain savedQuestion = questionService.updateQuestion(questionId, updatedQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }
}