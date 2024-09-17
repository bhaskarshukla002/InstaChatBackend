package com.example.InstaChat.comment;

import com.example.InstaChat.posts.PostRepository;
import com.example.InstaChat.posts.model.Post;
import com.example.InstaChat.user.User;
import com.example.InstaChat.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService  {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public String postComment(CommentDTO commentDTO) {
        Post post = postRepository.findById(Long.parseLong(commentDTO.getPostId())).orElse(null);
        User user = userRepository.findByUserName(commentDTO.getUsername()).get();

        if (post == null || user == null) {
            return "Post or User not found";
        }

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentDTO.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
        return "Comment added successfully";
    }
}

