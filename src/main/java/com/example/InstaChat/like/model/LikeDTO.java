package com.example.InstaChat.like.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LikeDTO {
    String postId;
    String username;
    String name;
    boolean isFollowedByUser;
    String profileUrl;
}
