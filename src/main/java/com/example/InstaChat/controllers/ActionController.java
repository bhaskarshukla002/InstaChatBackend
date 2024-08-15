package com.example.InstaChat.controllers;

import com.example.InstaChat.dto.CommentDTO;
import com.example.InstaChat.dto.FollowDTO;
import com.example.InstaChat.dto.LikeDTO;
import com.example.InstaChat.dto.PostUploadDTO;
import com.example.InstaChat.services.PostServiceImpl;
import com.example.InstaChat.servicesinterface.CommentService;
import com.example.InstaChat.servicesinterface.FollowService;
import com.example.InstaChat.servicesinterface.LikeService;
import com.example.InstaChat.servicesinterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActionController {

    @Autowired()
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FollowService followService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/uploadpost")
    ResponseEntity<?> uploadPost(@RequestBody PostUploadDTO post){
//  uploadPost username postcontenturl and caption
        String response = postService.uploadPost(post);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/uploadcomment")
    ResponseEntity<?> uploadCommentOnPost(@RequestBody CommentDTO commentDTO){
//  will give only postId and username and content all other values will be null
//  and the post is already Commented then it should be handled liked by user then is will be unliked
        String response = commentService.postComment(commentDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/likepost")
    ResponseEntity<?> likePost(@RequestBody LikeDTO like){
//  will give only postId and username all other values will be null and the post is already liked by user then is will be unliked
        String response = likeService.postLike(like);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getpostcomments")
    ResponseEntity<?> getPostComments(@RequestParam String postId,@RequestParam String userId){
//  will send back if any one whom RequestUser is following in following list of RequestUser with remaining random comments
//  totaling to one Hundred comments
        List<CommentDTO> comments = postService.getPostComments(postId, userId);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/getpostlikes")
    ResponseEntity<?> getPostLikes(@RequestParam String postId,@RequestParam String userId){
//  will send back if any one whom RequestUser is following in following list of RequestUser with remaining random
//  likes totaling to one Hundred likes
//  if wanted to get particular like of a person he could maybe send username of that person as a search query
        List<LikeDTO> likes = postService.getPostLikes(postId, userId);
        return ResponseEntity.ok(likes);
    }
    @PostMapping("/follow")
    ResponseEntity<?> follow(@RequestBody FollowDTO follow){
        String response = followService.followUser();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/followers")
    ResponseEntity<?> getFollowers(@RequestParam String username,@RequestParam String userId){
        String response = followService.getFollowerList();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/followings")
    ResponseEntity<?> getFollowings(@RequestParam String username,@RequestParam String userId){
        String response = followService.getFollowingList();
        return ResponseEntity.ok().body(response);
    }
}
