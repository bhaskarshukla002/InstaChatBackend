package com.example.InstaChat.services;

import com.example.InstaChat.entities.FileData;
import com.example.InstaChat.entities.Image;
import com.example.InstaChat.repositories.FileDataRepository;
import com.example.InstaChat.repositories.ImageRepository;
import com.example.InstaChat.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    private String FOLDERPATH="C:\\Users\\javis\\Desktop\\myfiles";

    public Image uploadImageToDatabase(MultipartFile file) throws IOException {

        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());


        image.setData(ImageUtils.compressImage(file.getBytes()));
        return imageRepository.save(image);
    }

    public Optional<Image> downloadImageFromDataBase(Long id) {
        return imageRepository.findById(id);
    }

    public FileData uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDERPATH+"\\"+file.getOriginalFilename();

        file.transferTo(new File(filePath));
        return fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build()
        );
    }

    public Optional<FileData> downloadImageFromFileSystem(Long id) throws IOException {
         return fileDataRepository.findById(id);
    }

}
