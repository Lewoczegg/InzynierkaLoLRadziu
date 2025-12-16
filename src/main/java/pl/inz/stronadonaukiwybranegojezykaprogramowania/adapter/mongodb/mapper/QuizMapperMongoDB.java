package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import java.util.stream.Collectors;

@Component
public class QuizMapperMongoDB {

    private final QuestionMapperMongoDB questionMapper;

    public QuizMapperMongoDB(QuestionMapperMongoDB questionMapper) {
        this.questionMapper = questionMapper;
    }

    public QuizDomain toDomain(QuizDocument document) {
        if (document == null) {
            return null;
        }

        QuizDomain quiz = QuizDomain.builder()
                .quizId(document.getId())
                .title(document.getTitle())
                .build();

        // Map questions if they exist
        if (document.getQuestions() != null) {
            quiz.setQuestions(document.getQuestions().stream()
                    .map(q -> questionMapper.toDomain(q, document.getId()))
                    .collect(Collectors.toList()));
        }

        return quiz;
    }

    public QuizDocument toDocument(QuizDomain domain) {
        if (domain == null) {
            return null;
        }

        QuizDocument document = new QuizDocument();
        document.setId(domain.getQuizId());
        document.setTitle(domain.getTitle());
        
        return document;
    }
}
