package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizResultEmbedded;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.QuizResultDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.time.ZoneId;

@Component
public class QuizResultMapperMongoDB {

    public QuizResultDomain toDomain(QuizResultEmbedded embedded, UserDomain user, QuizDomain quiz) {
        if (embedded == null) {
            return null;
        }

        QuizResultDomain domain = new QuizResultDomain();
        domain.setUser(user);
        domain.setQuiz(quiz);
        domain.setPoints(embedded.getPoints());
        
        if (embedded.getCompletedAt() != null) {
            domain.setCompletedAt(embedded.getCompletedAt().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        
        return domain;
    }

    public QuizResultEmbedded toEmbedded(QuizResultDomain domain) {
        if (domain == null) {
            return null;
        }

        QuizResultEmbedded embedded = new QuizResultEmbedded();
        
        if (domain.getQuiz() != null) {
            // Will be set as ObjectId reference when saving
            embedded.setQuizId(null); // MongoDB ObjectId will be resolved from quizIdLegacy
        }
        
        embedded.setPoints(domain.getPoints());
        
        if (domain.getCompletedAt() != null) {
            embedded.setCompletedAt(domain.getCompletedAt().atZone(ZoneId.systemDefault()).toInstant());
        }
        
        return embedded;
    }
}
