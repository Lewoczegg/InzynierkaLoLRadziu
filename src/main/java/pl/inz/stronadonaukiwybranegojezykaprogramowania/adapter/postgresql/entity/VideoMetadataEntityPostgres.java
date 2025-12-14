package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.VideoFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_metadata")
public class VideoMetadataEntityPostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverrides({
        @AttributeOverride(name = "fileName", column = @Column(name = "file.file_name")),
        @AttributeOverride(name = "filePath", column = @Column(name = "file.file_path")),
        @AttributeOverride(name = "fileSize", column = @Column(name = "file.file_size"))
    })
    @Embedded
    private VideoFile file;
}
