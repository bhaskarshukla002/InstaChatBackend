package com.example.InstaChat.dto;

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
