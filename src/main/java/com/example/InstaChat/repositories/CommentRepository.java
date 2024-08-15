package com.example.InstaChat.repositories;

import com.example.InstaChat.entities.Comment;
import com.example.InstaChat.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}