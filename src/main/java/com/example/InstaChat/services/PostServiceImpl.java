package com.example.InstaChat.services;

import com.example.InstaChat.dto.CommentDTO;
import com.example.InstaChat.dto.LikeDTO;
import com.example.InstaChat.dto.PostUploadDTO;
import com.example.InstaChat.entities.Comment;
import com.example.InstaChat.entities.Like;
import com.example.InstaChat.entities.Post;
import com.example.InstaChat.entities.User;
import com.example.InstaChat.repositories.CommentRepository;
import com.example.InstaChat.repositories.LikeRepository;
import com.example.InstaChat.repositories.PostRepository;
import com.example.InstaChat.repositories.UserRepository;
import com.example.InstaChat.servicesinterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public String uploadPost(PostUploadDTO postDTO) {
        Optional<User> user = userRepository.findByUserName(postDTO.getUsername());
        if (user.isPresent()) {
            return "User not found";
        }

        Post post = Post.builder()
                .user(user.get())
                .contentUrl(postDTO.getPostContentUrl())
                .caption(postDTO.getCaption())
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
        return "Post uploaded successfully";
    }

    @Override
    public List<LikeDTO> getPostLikes(String postId, String userId) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElse(null);
        if (post == null) {
            return List.of();
        }

        List<Like> likes = likeRepository.findByPost(post);

        return likes.stream().map(like -> {
            User user = like.getUser();
            boolean isFollowedByUser = user.getFollowers().stream().anyMatch(follower -> follower.getUserId().equals(userId));
            return LikeDTO.builder()
                    .postId(postId)
                    .username(user.getUsername())
                    .name(user.getName())
                    .isFollowedByUser(isFollowedByUser)
                    .profileUrl(user.getProfilePicture())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getPostComments(String postId, String userId) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElse(null);
        if (post == null) {
            return List.of();
        }

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream().map(comment -> {
            User user = comment.getUser();
            boolean isFollowedByUser = user.getFollowers().stream().anyMatch(follower -> follower.getUserId().equals(userId));
            return CommentDTO.builder()
                    .postId(postId)
                    .username(user.getUsername())
                    .name(user.getName())
                    .content(comment.getContent())
                    .isFollowedByUser(isFollowedByUser)
                    .profileUrl(user.getProfilePicture())
                    .build();
        }).collect(Collectors.toList());
    }
}
