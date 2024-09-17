package com.example.InstaChat.posts;

import com.example.InstaChat.posts.model.PostUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    private PostService postService;


    @PostMapping(value = "/uploadpost",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadPost(@RequestParam(value = "content") MultipartFile content
            ,@RequestParam(name = "")String username
            ,@RequestParam(name = "caption") String caption
    ) throws IOException {
//  uploadPost username postcontenturl and caption
        String response = postService.uploadPost(username,caption,content);
        return ResponseEntity.ok(response);
    }


}

