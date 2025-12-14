package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.VideoFile;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.VideoFileType;

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

    @Type(VideoFileType.class)
    @Column(name = "file", columnDefinition = "video_file")
    private VideoFile file;
}
