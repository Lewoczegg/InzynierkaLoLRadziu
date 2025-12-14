package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoFile implements Serializable {
    private String fileName;
    private String filePath;
    private Long fileSize;
}
