package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.QuizDocument;

import java.util.Optional;

@Repository
public interface QuizRepositoryMongoDB extends MongoRepository<QuizDocument, Long> {
}
