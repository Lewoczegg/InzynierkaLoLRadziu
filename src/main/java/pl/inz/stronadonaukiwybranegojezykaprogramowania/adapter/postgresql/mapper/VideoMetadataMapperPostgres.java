package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.VideoMetadataEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.VideoFile;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

@Component
public class VideoMetadataMapperPostgres {

    public VideoMetadataDomain toDomain(VideoMetadataEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        return VideoMetadataDomain.builder()
                .id(entity.getId())
                .fileName(entity.getFile() != null ? entity.getFile().getFileName() : null)
                .filePath(entity.getFile() != null ? entity.getFile().getFilePath() : null)
                .fileSize(entity.getFile() != null ? entity.getFile().getFileSize() : null)
                .build();
    }

    public VideoMetadataEntityPostgres toEntity(VideoMetadataDomain domain) {
        if (domain == null) {
            return null;
        }
        
        VideoFile videoFile = VideoFile.builder()
                .fileName(domain.getFileName())
                .filePath(domain.getFilePath())
                .fileSize(domain.getFileSize())
                .build();
        
        return VideoMetadataEntityPostgres.builder()
                .id(domain.getId())
                .file(videoFile)
                .build();
    }
}
