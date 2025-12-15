package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.UserDocument;

import java.util.Optional;

@Repository
public interface UserRepositoryMongoDB extends MongoRepository<UserDocument, Long> {
    
    UserDocument findByUsername(String username);
}
