package com.example.InstaChat.like;

import com.example.InstaChat.posts.model.Post;
import com.example.InstaChat.like.model.Like;
import com.example.InstaChat.like.model.LikeDTO;
import com.example.InstaChat.user.User;
import com.example.InstaChat.posts.PostRepository;
import com.example.InstaChat.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    public String postLike(LikeDTO likeDTO) {
        Post post = postRepository.findById(Long.parseLong(likeDTO.getPostId())).orElse(null);
        User user = userRepository.findByUserName(likeDTO.getUsername()).get();

        if (post == null || user == null) {
            return "Post or User not found";
        }

        Like like = likeRepository.findByPostAndUser( post,user);
        if (like != null) {
            likeRepository.delete(like);
            return "Post unliked successfully";
        } else {
            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(user);
            newLike.setCreatedAt(LocalDateTime.now());
            likeRepository.save(newLike);
            return "Post liked successfully";
        }
    }
}
