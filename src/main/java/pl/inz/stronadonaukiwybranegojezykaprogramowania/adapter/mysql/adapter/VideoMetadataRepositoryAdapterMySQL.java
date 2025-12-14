package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.VideoMetadataRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.VideoMetadataMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.VideoMetadataRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class VideoMetadataRepositoryAdapterMySQL implements VideoMetadataRepositoryAdapter {

    private final VideoMetadataRepositoryMySQL videoMetadataRepository;
    private final VideoMetadataMapperMySQL videoMetadataMapper;

    public VideoMetadataRepositoryAdapterMySQL(VideoMetadataRepositoryMySQL videoMetadataRepository, VideoMetadataMapperMySQL videoMetadataMapper) {
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
