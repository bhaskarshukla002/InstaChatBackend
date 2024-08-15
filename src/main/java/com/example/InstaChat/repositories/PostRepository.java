package com.example.InstaChat.repositories;

import com.example.InstaChat.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}