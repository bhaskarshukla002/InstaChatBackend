package com.example.InstaChat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUserName(String username);

    boolean existsByUserNameAndFollowing(String username, User follower);
}
