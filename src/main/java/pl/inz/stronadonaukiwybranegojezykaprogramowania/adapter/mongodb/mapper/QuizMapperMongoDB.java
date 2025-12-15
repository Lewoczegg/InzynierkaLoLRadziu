package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;

@Component
public class QuizMapperMongoDB {

    public QuizDomain toDomain(QuizDocument document) {
        if (document == null) {
            return null;
        }

        return QuizDomain.builder()
                .quizId(document.getId())
                .title(document.getTitle())
                .build();
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
