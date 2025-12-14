package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataDomain {

    private Long id;

    private String fileName;
    
    private String filePath;
    
    private Long fileSize;
}
