package com.example.InstaChat.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserChatRepository extends JpaRepository<UserChat,String> {
    Optional<UserChat> findBySenderIdAndRecipientId(String senderId, String recipientId);

    Optional<List<UserChat>> findAllBySender_Username(String username);
}
