package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.VideoMetadata;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.VideoMetadataService;

@RestController
@RequestMapping("/VideoMetadata")
public class VideoMetadataController {

    private final VideoMetadataService videoService;

    public VideoMetadataController(VideoMetadataService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("video") MultipartFile file) {
        try {
            VideoMetadata metadata = videoService.saveVideo(file);
            return ResponseEntity.ok("File uploaded successfully: " + metadata.getFileName());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/stream/{fileName}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String fileName) {
        try {
            Resource resource = videoService.loadVideoAsResource(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
