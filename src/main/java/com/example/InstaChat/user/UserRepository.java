package com.example.InstaChat.repositories;

import com.example.InstaChat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUserName(String username);
}
