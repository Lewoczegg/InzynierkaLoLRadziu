package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuestionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuizRepositoryAdapter;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepositoryAdapter questionRepositoryAdapter;

    private final QuizRepositoryAdapter quizRepositoryAdapter;

    public QuestionService(QuestionRepositoryAdapter questionRepositoryAdapter, QuizRepositoryAdapter quizRepositoryAdapter) {
        this.questionRepositoryAdapter = questionRepositoryAdapter;
        this.quizRepositoryAdapter = quizRepositoryAdapter;
    }

    public QuestionDomain createQuestion(Long quizId, QuestionDomain question) {
        quizRepositoryAdapter.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
        question.setQuizId(quizId);
        return questionRepositoryAdapter.save(question);
    }

    public QuestionDomain getQuestionById(Long questionId) {
        return questionRepositoryAdapter.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));
    }

    public List<QuestionDomain> getAllQuestions() {
        return questionRepositoryAdapter.findAll();
    }

    public QuestionDomain updateQuestion(Long questionId, QuestionDomain updatedQuestion) {
        QuestionDomain question = questionRepositoryAdapter.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));
        question.setContent(updatedQuestion.getContent());
        question.setOptions(updatedQuestion.getOptions());
        question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
        return questionRepositoryAdapter.save(question);
    }

    public void deleteQuestion(Long questionId) {
        QuestionDomain question = questionRepositoryAdapter.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));
        questionRepositoryAdapter.deleteById(question.getQuestionId());
    }
}
