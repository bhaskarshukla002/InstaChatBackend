package com.example.InstaChat.comment;


import com.example.InstaChat.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/uploadcomment")
    ResponseEntity<?> uploadCommentOnPost(@RequestBody CommentDTO commentDTO){
//  will give only postId and username and content all other values will be null
//  and the post is already Commented then it should be handled liked by user then is will be unliked
        String response = commentService.postComment(commentDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getpostcomments")
    ResponseEntity<?> getPostComments(@RequestParam String postId,@RequestParam String userId) {
//  will send back if any one whom RequestUser is following in following list of RequestUser with remaining random comments
//  totaling to one Hundred comments
        List<CommentDTO> comments = postService.getPostComments(postId, userId);
        return ResponseEntity.ok(comments);
    }
}
