package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.VideoMetadataEntityMySQL;

@Repository
public interface VideoMetadataRepositoryMySQL extends JpaRepository<VideoMetadataEntityMySQL, Long> {
}
