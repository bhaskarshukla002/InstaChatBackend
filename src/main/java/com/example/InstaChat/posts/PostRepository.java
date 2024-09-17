package com.example.InstaChat.feed.repository;

import com.example.InstaChat.feed.entities.Post;
import com.example.InstaChat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIn(List<User> users);
}