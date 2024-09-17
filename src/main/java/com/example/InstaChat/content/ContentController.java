package com.example.InstaChat.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ContentController {

    @Autowired
    private ContentService imageService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getContent(@PathVariable long id) {

        System.out.println("Fetching image with id: " + id);
        try {
            // Fetch the file from the file system
            Optional<File> file = imageService.downloadImageFromFileSystem(id);

            // If the file is found, process it
            if (file.isPresent()) {
                String filePath = file.get().getPath();
                byte[] fileContent = Files.readAllBytes(new File(filePath).toPath());

                // Get the file type
                String fileType = imageService.getFileType(file.get());
                System.out.println("Image found with id: " + id);

                // Return the file as a response
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf(fileType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get().getName() + "\"")
                        .body(fileContent);
            }

            // If file is not found, return 404
            System.out.println("Image not found with id: " + id);
        } catch (Exception e) {
            System.out.println("IO Error for id: " + id + " - " + e.getMessage());
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
    }

}