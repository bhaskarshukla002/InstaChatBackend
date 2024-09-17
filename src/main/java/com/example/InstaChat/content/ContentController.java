package com.example.InstaChat.cdn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/images")
public class ContentController {

    @Autowired
    private ContentService imageService;


//    @PostMapping("/uploadDb")
//    public ResponseEntity<?> uploadImageDB(@RequestParam("file") MultipartFile file) {
////                Image imaget=imageRepository.save(image);
////        if(imaget!=null){
////            return "fileUploaded successfully : "+ file.getOriginalFilename();
////
////        }
//        try {
//
//            Image image=imageService.uploadImageToDatabase(file);
//            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully with id : " + image.getId());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
//        }
//    }

//    @GetMapping("/downloadDb/{id}")
//    public ResponseEntity<?> getImageDB(@PathVariable long id) {
//
//        System.out.println("Image not found with id: " + id);
//        Optional<Image> imageOptional = imageService.downloadImageFromDataBase(id);
//
//        if (imageOptional.isPresent()) {
//            Image imagedb = imageOptional.get();
//            byte[] image= ImageUtils.decompressImage(imagedb.getData());
//
//            System.out.println("Image found with id: " + id);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .contentType(MediaType.valueOf(imagedb.getType() ))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagedb.getType() + "\"")
//                    .body(image);
//        }
//        System.out.println("Image not found with id: " + id);
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//
//    @PostMapping("/uploadFs")
//    public ResponseEntity<?> uploadImageFS(@RequestParam("file") MultipartFile file) {
//        try {
//            FileData filedb=imageService.uploadImageToFileSystem(file);
//            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully with id : " + filedb.getId());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContent(@PathVariable long id)  {

        System.out.println("Image not found with id: " + id);
        try {

//            Optional<File> file = imageService.downloadImageFromFileSystem(id);
//            if (id != null) {
                String filePath= file.get().getFilePath();
                byte[] filefs= Files.readAllBytes(new File(filePath).toPath());
                System.out.println("Image found with id: " + id);
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf(file.get().getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get().getType() + "\"")
                        .body(filefs);
//            }
//            System.out.println("Image not found with id: " + id);
        }
        catch(Exception e){
            System.out.println("IO Error: " + id);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}