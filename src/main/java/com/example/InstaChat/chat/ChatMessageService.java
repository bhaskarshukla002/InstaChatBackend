package com.example.InstaChat.chat;

import com.example.InstaChat.user.User;
import com.example.InstaChat.user.UserRepository;
import com.example.InstaChat.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private UserChatService userChatService;

    public ChatMessage save(MessageDTO chatMessage){
        var userChat=userChatService.getUserChatById(chatMessage.getChatId());
        var nchatMessage= ChatMessage.builder().userChat(userChat).content(chatMessage.getContent()).build();
        repository.save(nchatMessage);
        return nchatMessage;
    }

    public List<ChatMessage> getAllUserChatMessage(String chatId){
        return repository.findByUserChatId(chatId);
    }

    public List<UserChat> getAllUserChat(String username) {
        return userChatService.getAllUserChat(username);
    }

    public UserChat createUserChat(String senderUsername, String receiverUsername) {
        return userChatService.createUserChat(senderUsername,receiverUsername);
    }
}
