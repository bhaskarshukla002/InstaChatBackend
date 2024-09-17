package com.example.InstaChat.chat;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long id;

    private String chatId;

    private String senderUsername;
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();

    private LocalDateTime seenAt;

    private LocalDateTime deliveredAt;

    // Optionally add additional fields or methods as needed
}