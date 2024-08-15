package com.example.InstaChat.services;

import com.example.InstaChat.dto.LikeDTO;
import com.example.InstaChat.entities.Like;
import com.example.InstaChat.entities.Post;
import com.example.InstaChat.entities.User;
import com.example.InstaChat.repositories.LikeRepository;
import com.example.InstaChat.repositories.PostRepository;
import com.example.InstaChat.repositories.UserRepository;
import com.example.InstaChat.servicesinterface.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
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
