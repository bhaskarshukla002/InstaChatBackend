package com.example.InstaChat.chat;

import com.example.InstaChat.user.User;
import com.example.InstaChat.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserChatService {
    @Autowired
    UserChatRepository repository;
    @Autowired
    private UserRepository userRepository;


    UserChat createUserChat(String senderUsername, String receiverUsername) {

        User sender=userRepository.findByUserName(senderUsername).get();
        User receiver=userRepository.findByUserName(receiverUsername).get();
        UserChat senderRoom=UserChat.builder().sender(sender).recipient(receiver).build();
        UserChat recipientRoom=UserChat.builder().sender(receiver).recipient(sender).build();
        repository.save(senderRoom);
        repository.save(recipientRoom);
        return senderRoom;
    }

    public List<UserChat> getAllUserChat(String username) {
        return repository.findAllBySender_Username(username).get();
    }
    public UserChat getUserChatById(String id) {
        return repository.getReferenceById(id);
    }
}
