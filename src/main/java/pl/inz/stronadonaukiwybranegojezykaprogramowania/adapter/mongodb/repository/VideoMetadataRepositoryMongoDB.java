package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.VideoMetadataDocument;

import java.util.Optional;

@Repository
public interface VideoMetadataRepositoryMongoDB extends MongoRepository<VideoMetadataDocument, Long> {
}
