package com.example.InstaChat.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatNotification {
    private long id;
    private String senderId;
    private String recipientId;
    private String content;
}
