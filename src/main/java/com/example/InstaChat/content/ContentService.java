package com.example.InstaChat.cdn;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class ContentService {

    private String FOLDERPATH="C:\\Users\\javis\\Desktop\\myfiles";

    public Optional<File> downloadImageFromFileSystem(long id) {
        try {
            // Construct the file path based on the ID
            String filePath = FOLDERPATH + "/" + id + ".jpg";  // Assuming files are stored as <id>.jpg

            File file = new File(filePath);
            if (file.exists() && !file.isDirectory()) {
                return Optional.of(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Method to return the content type based on the file extension
    public String getFileType(File file) throws Exception {
        return Files.probeContentType(file.toPath());
    }

}
