package com.example.InstaChat.services;

import com.example.InstaChat.entities.User;
import com.example.InstaChat.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user;
        if (input.contains("@")) {
            // Login with email
            user = userRepository.findByEmail(input)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + input));
        } else {
            // Login with username
            user = userRepository.findByUserName(input)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + input));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
