package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

import java.util.List;
import java.util.Optional;

public interface VideoMetadataRepositoryAdapter {
    VideoMetadataDomain save(VideoMetadataDomain videoMetadata);
    Optional<VideoMetadataDomain> findById(Long id);
    List<VideoMetadataDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
