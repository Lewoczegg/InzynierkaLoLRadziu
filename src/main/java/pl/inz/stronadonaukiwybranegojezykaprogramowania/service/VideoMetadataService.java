package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.VideoMetadata;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.VideoMetadataRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoMetadataService {
    private final Path fileStorageLocation = Paths.get("src/videos").toAbsolutePath().normalize();


    private final VideoMetadataRepository videoMetadataRepository;

    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
    }

    public VideoMetadata saveVideo(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        try {
            File directory = new File(fileStorageLocation.toString());
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = fileStorageLocation + "/" + file.getOriginalFilename();
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            VideoMetadata metadata = new VideoMetadata();
            metadata.setFileName(file.getOriginalFilename());
            metadata.setFilePath(filePath);
            metadata.setFileSize(file.getSize());
            return videoMetadataRepository.save(metadata);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    public Resource loadVideoAsResource(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

