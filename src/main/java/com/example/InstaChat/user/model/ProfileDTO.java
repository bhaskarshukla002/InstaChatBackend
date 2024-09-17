package com.example.InstaChat.user.model;

import com.example.InstaChat.posts.model.Post;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfileDTO {
    String userId;
    String name;
    String username;
    String email;
    String bio;
    String profileUrl;
    int followerCount;
    int followingCount;
    List<Post> posts;
}
