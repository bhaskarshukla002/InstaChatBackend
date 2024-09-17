package com.example.InstaChat.like;
import com.example.InstaChat.posts.model.Post;
import com.example.InstaChat.like.model.Like;
import com.example.InstaChat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostAndUser(Post post,User user);

    List<Like> findByPost(Post post);
}