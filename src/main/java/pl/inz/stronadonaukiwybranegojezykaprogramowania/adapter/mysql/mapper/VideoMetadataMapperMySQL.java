package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.VideoMetadataEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

@Component
public class VideoMetadataMapperMySQL {

    public VideoMetadataDomain toDomain(VideoMetadataEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        return VideoMetadataDomain.builder()
                .id(entity.getId())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();
    }

    public VideoMetadataEntityMySQL toEntity(VideoMetadataDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return VideoMetadataEntityMySQL.builder()
                .id(domain.getId())
                .fileName(domain.getFileName())
                .filePath(domain.getFilePath())
                .fileSize(domain.getFileSize())
                .build();
    }
}
