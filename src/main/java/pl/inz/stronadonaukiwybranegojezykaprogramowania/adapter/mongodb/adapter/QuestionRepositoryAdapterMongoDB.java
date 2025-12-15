package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.QuestionRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuestionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.QuestionMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.QuizRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class QuestionRepositoryAdapterMongoDB implements QuestionRepositoryAdapter {

    private final QuizRepositoryMongoDB quizRepository;
    private final QuestionMapperMongoDB questionMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public QuestionRepositoryAdapterMongoDB(QuizRepositoryMongoDB quizRepository, 
                                           QuestionMapperMongoDB questionMapper,
                                           SequenceGeneratorService sequenceGeneratorService) {
        this.quizRepository = quizRepository;
        this.questionMapper = questionMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public QuestionDomain save(QuestionDomain question) {
        Long quizId = question.getQuizId();
        
        Optional<QuizDocument> quizOpt = quizRepository.findById(quizId);
        if (quizOpt.isEmpty()) {
            throw new RuntimeException("Quiz not found: " + quizId);
        }
        
        QuizDocument quizDoc = quizOpt.get();
        List<QuestionEmbedded> questions = quizDoc.getQuestions();
        
        // Initialize list if null
        if (questions == null) {
            questions = new java.util.ArrayList<>();
            quizDoc.setQuestions(questions);
        }
        
        // Generate ID if new question
        if (question.getQuestionId() == null) {
            question.setQuestionId(sequenceGeneratorService.generateSequence("question_sequence"));
        }
        
        boolean found = false;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() != null && 
                questions.get(i).getQuestionId().equals(question.getQuestionId())) {
                questions.set(i, questionMapper.toEmbedded(question));
                found = true;
                break;
            }
        }
        
        if (!found) {
            questions.add(questionMapper.toEmbedded(question));
        }
        
        quizRepository.save(quizDoc);
        return question;
    }

    @Override
    public Optional<QuestionDomain> findById(Long id) {
        return quizRepository.findAll().stream()
                .flatMap(quizDoc -> quizDoc.getQuestions().stream()
                        .filter(question -> question.getQuestionId().equals(id))
                        .map(q -> questionMapper.toDomain(q, quizDoc.getId())))
                .findFirst();
    }

    @Override
    public List<QuestionDomain> findAll() {
        return quizRepository.findAll().stream()
                .flatMap(quizDoc -> quizDoc.getQuestions().stream()
                        .map(q -> questionMapper.toDomain(q, quizDoc.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.findAll().forEach(quizDoc -> {
            quizDoc.getQuestions().removeIf(question -> 
                    question.getQuestionId().equals(id));
            quizRepository.save(quizDoc);
        });
    }

    @Override
    public boolean existsById(Long id) {
        return quizRepository.findAll().stream()
                .flatMap(quizDoc -> quizDoc.getQuestions().stream())
                .anyMatch(question -> question.getQuestionId().equals(id));
    }

    @Override
    public long count() {
        return quizRepository.findAll().stream()
                .mapToLong(quizDoc -> quizDoc.getQuestions().size())
                .sum();
    }
}
