package com.example.InstaChat.feed.entities;

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
