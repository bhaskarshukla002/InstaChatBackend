package com.example.InstaChat.likeFollowComment.repository;

import com.example.InstaChat.likeFollowComment.entities.Comment;
import com.example.InstaChat.feed.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}