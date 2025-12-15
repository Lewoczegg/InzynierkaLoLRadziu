package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.VideoMetadataDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.VideoMetadataDomain;

@Component
public class VideoMetadataMapperMongoDB {

    public VideoMetadataDomain toDomain(VideoMetadataDocument document) {
        if (document == null) {
            return null;
        }

        return VideoMetadataDomain.builder()
                .id(document.getId())
                .fileName(document.getFileName())
                .filePath(document.getFilePath())
                .fileSize(document.getFileSize())
                .build();
    }

    public VideoMetadataDocument toDocument(VideoMetadataDomain domain) {
        if (domain == null) {
            return null;
        }

        VideoMetadataDocument document = new VideoMetadataDocument();
        document.setId(domain.getId());
        document.setFileName(domain.getFileName());
        document.setFilePath(document.getFilePath());
        document.setFileSize(domain.getFileSize());
        
        return document;
    }
}
