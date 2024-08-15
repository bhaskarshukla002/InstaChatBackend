package com.example.InstaChat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDTO {
    String postId;
    String username;
    String name;
    String content;
    boolean isFollowedByUser;
    String profileUrl;
}
