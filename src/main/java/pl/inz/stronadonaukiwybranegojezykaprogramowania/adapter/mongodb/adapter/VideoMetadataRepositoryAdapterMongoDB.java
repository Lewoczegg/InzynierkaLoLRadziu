package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.VideoMetadataRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.SequenceGeneratorService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper.VideoMetadataMapperMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.repository.VideoMetadataRepositoryMongoDB;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongodb")
@Component
public class VideoMetadataRepositoryAdapterMongoDB implements VideoMetadataRepositoryAdapter {

    private final VideoMetadataRepositoryMongoDB videoMetadataRepository;
    private final VideoMetadataMapperMongoDB videoMetadataMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public VideoMetadataRepositoryAdapterMongoDB(VideoMetadataRepositoryMongoDB videoMetadataRepository, VideoMetadataMapperMongoDB videoMetadataMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.videoMetadataMapper = videoMetadataMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public VideoMetadataDomain save(VideoMetadataDomain videoMetadata) {
        var videoMetadataDoc = videoMetadataMapper.toDocument(videoMetadata);
        if (videoMetadataDoc.getId() == null) {
            videoMetadataDoc.setId(sequenceGeneratorService.generateSequence("video_metadata"));
        }
        return videoMetadataMapper.toDomain(videoMetadataRepository.save(videoMetadataDoc));
    }

    @Override
    public Optional<VideoMetadataDomain> findById(Long id) {
        return videoMetadataRepository.findById(id)
                .map(videoMetadataMapper::toDomain);
    }

    @Override
    public List<VideoMetadataDomain> findAll() {
        return videoMetadataRepository.findAll().stream()
                .map(videoMetadataMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        videoMetadataRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return videoMetadataRepository.existsById(id);
    }

    @Override
    public long count() {
        return videoMetadataRepository.count();
    }
}
