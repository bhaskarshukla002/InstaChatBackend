package com.example.InstaChat.likeFollowComment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {
    String username;
    String name;
    boolean isFollowedByUser;
    String profileUrl;
}
