package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.VideoMetadataRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.VideoMetadataMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.VideoMetadataRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class VideoMetadataRepositoryAdapterPostgres implements VideoMetadataRepositoryAdapter {

    private final VideoMetadataRepositoryPostgres videoMetadataRepository;
    private final VideoMetadataMapperPostgres videoMetadataMapper;

    public VideoMetadataRepositoryAdapterPostgres(VideoMetadataRepositoryPostgres videoMetadataRepository, VideoMetadataMapperPostgres videoMetadataMapper) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.videoMetadataMapper = videoMetadataMapper;
    }

    @Override
    public VideoMetadataDomain save(VideoMetadataDomain videoMetadata) {
        return videoMetadataMapper.toDomain(
                videoMetadataRepository.save(videoMetadataMapper.toEntity(videoMetadata))
        );
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
