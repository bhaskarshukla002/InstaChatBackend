package com.example.InstaChat.posts;

import com.example.InstaChat.comment.CommentRepository;
import com.example.InstaChat.like.model.LikeDTO;
import com.example.InstaChat.like.model.Like;
import com.example.InstaChat.comment.Comment;
import com.example.InstaChat.comment.CommentDTO;
import com.example.InstaChat.posts.model.Post;
import com.example.InstaChat.user.User;
import com.example.InstaChat.like.LikeRepository;
import com.example.InstaChat.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService  {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    public String uploadPost(String username, String caption, MultipartFile content) throws IOException {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            return "User not found";
        }

        String FOLDERPATH="C:\\Users\\javis\\Desktop\\myfiles";
        String filePath=FOLDERPATH +"\\"+content.getOriginalFilename();
        content.transferTo(new File(filePath));

        Post post = Post.builder()
                .user(user.get())
                .caption(caption)
                .createdAt(LocalDateTime.now())
                .build();


        post=postRepository.save(post);
        post.setContentUrl(String.valueOf(post.getPostId()));

        return "Post uploaded successfully";
    }
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
