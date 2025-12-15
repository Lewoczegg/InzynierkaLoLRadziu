package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.CourseDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepositoryMongoDB extends MongoRepository<CourseDocument, Long> {
    
    List<CourseDocument> findByTitleLevel(Title title);
    
    List<CourseDocument> findByTitleLevelIn(Collection<Title> titles);
}
