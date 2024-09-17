package com.example.InstaChat.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;


    @MessageMapping("/message")
    public void processMessage(@Payload MessageDTO chatMessage) {
        // Save the message in the database
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        // Notify the sender
        messagingTemplate.convertAndSendToUser(
                savedMsg.getUserChat().getSender().getUsername(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .recipientId(savedMsg.getUserChat().getRecipient().getUsername())
                        .senderId(savedMsg.getUserChat().getSender().getUsername())
                        .content(savedMsg.getContent())
                        .build()
        );

        // Notify the recipient
        messagingTemplate.convertAndSendToUser(
                savedMsg.getUserChat().getRecipient().getUsername(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .recipientId(savedMsg.getUserChat().getRecipient().getUsername())
                        .senderId(savedMsg.getUserChat().getSender().getUsername())
                        .content(savedMsg.getContent())
                        .build()
        );
    }

    @PostMapping("/chat/{sender}/{receiver}")
    public ResponseEntity<UserChat> createUserChat(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver) {
        return ResponseEntity.ok(chatMessageService.createUserChat(sender, receiver));
    }

    @GetMapping("/chat/{username}")
    public ResponseEntity<?> getAllUserChat(@PathVariable String username){
        return ResponseEntity.ok(chatMessageService.getAllUserChat(username));
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<ChatMessage>> getAllUserChatMessage(@PathVariable("chatId") String chatId){
        return ResponseEntity.ok(chatMessageService.getAllUserChatMessage(chatId));
    }
}

