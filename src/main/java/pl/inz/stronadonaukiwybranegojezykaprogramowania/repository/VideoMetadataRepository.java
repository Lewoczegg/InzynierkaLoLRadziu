package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.VideoMetadata;

public interface VideoMetadataRepository extends JpaRepository<VideoMetadata, Long> {
}
