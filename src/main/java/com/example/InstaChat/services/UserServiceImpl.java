package com.example.InstaChat.services;

import com.example.InstaChat.dto.ProfileDTO;
import com.example.InstaChat.entities.User;
import com.example.InstaChat.repositories.UserRepository;
import com.example.InstaChat.servicesinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ProfileDTO getProfile(String userName) throws NoSuchElementException,NullPointerException {
//        User user=userRepository.findByUserName(userName).orElseThrow();
        User user=userRepository.findByEmail(userName).orElseThrow();

        return ProfileDTO.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .username(user.getUsername()).email(user.getEmail())
                .bio(user.getBio()).profileUrl(user.getProfilePicture()).followerCount(user.getFollowers().size())
                .followingCount(user.getFollowing().size()).posts(user.getPosts()).build();
    }

    @Override
    public String editProfile(String userName, ProfileDTO newDetails) throws NoSuchElementException {
        User user=userRepository.findByEmail(userName).orElseThrow();
        user.setName(newDetails.getName());
        user.setUserName(newDetails.getUsername());
        user.setBio(newDetails.getBio());
        user.setEmail(newDetails.getEmail());
        user.setProfilePicture(newDetails.getProfileUrl());
        userRepository.save(user);
        return "done";


    }

}
