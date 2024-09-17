package com.example.InstaChat.follow.model;

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
