package com.example.InstaChat.repositories;
import com.example.InstaChat.entities.Like;
import com.example.InstaChat.entities.Post;
import com.example.InstaChat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostAndUser(Post post,User user);

    List<Like> findByPost(Post post);
}