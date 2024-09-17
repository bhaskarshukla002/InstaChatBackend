package com.example.InstaChat.user;

import com.example.InstaChat.feed.entities.Post;
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
