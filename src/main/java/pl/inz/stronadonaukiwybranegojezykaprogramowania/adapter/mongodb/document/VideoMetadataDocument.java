package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "video_metadata")
public class VideoMetadataDocument {

    @Id
    private Long id; // Use Long ID directly (matches SQL)

    @Field("fileName")
    private String fileName;

    @Field("filePath")
    private String filePath;

    @Field("fileSize")
    private Long fileSize;
}
