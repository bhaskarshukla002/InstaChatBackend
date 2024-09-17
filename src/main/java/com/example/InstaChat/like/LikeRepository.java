package com.example.InstaChat.likeFollowComment.repository;
import com.example.InstaChat.likeFollowComment.entities.Like;
import com.example.InstaChat.feed.entities.Post;
import com.example.InstaChat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostAndUser(Post post,User user);

    List<Like> findByPost(Post post);
}