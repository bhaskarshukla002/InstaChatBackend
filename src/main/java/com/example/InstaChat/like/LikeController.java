package com.example.InstaChat.like;

import com.example.InstaChat.like.model.LikeDTO;
import com.example.InstaChat.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;

    @PostMapping("/likepost")
    ResponseEntity<?> likePost(@RequestBody LikeDTO like){
//  will give only postId and username all other values will be null and the post is already liked by user then is will be unliked
        String response = likeService.postLike(like);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getpostlikes")
    ResponseEntity<?> getPostLikes(@RequestParam String postId,@RequestParam String userId){
//  will send back if any one whom RequestUser is following in following list of RequestUser with remaining random
//  likes totaling to one Hundred likes
//  if wanted to get particular like of a person he could maybe send username of that person as a search query
        List<LikeDTO> likes = postService.getPostLikes(postId, userId);
        return ResponseEntity.ok(likes);
    }
}
