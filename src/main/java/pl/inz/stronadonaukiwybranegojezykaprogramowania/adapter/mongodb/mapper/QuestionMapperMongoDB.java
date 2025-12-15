package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuestionEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuestionDomain;

@Component
public class QuestionMapperMongoDB {

    public QuestionDomain toDomain(QuestionEmbedded embedded, Long quizId) {
        if (embedded == null) {
            return null;
        }

        return QuestionDomain.builder()
                .questionId(embedded.getQuestionId())
                .quizId(quizId)
                .content(embedded.getContent())
                .options(embedded.getOptions())
                .correctAnswer(embedded.getCorrectAnswer())
                .build();
    }

    public QuestionEmbedded toEmbedded(QuestionDomain domain) {
        if (domain == null) {
            return null;
        }

        QuestionEmbedded embedded = new QuestionEmbedded();
        embedded.setQuestionId(domain.getQuestionId());
        embedded.setContent(domain.getContent());
        embedded.setOptions(domain.getOptions());
        embedded.setCorrectAnswer(domain.getCorrectAnswer());
        
        return embedded;
    }
}
