package com.example.InstaChat.posts.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostUploadDTO {
    String username;
    String postContentUrl;
    String caption;
}
