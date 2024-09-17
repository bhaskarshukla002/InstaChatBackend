package com.example.InstaChat.chat;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_chat_id", nullable = false)
    private UserChat userChat;

    @Column(nullable = false)
    private String senderUsername;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    private LocalDateTime seenAt;

    private LocalDateTime deliveredAt;

    // Optionally add additional fields or methods as needed
}