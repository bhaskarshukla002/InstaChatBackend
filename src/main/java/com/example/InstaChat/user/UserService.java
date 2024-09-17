package com.example.InstaChat.user;

import com.example.InstaChat.user.model.ChangePasswordRequest;
import com.example.InstaChat.user.model.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {

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

    @Cacheable(value = "profiles", key = "#username")
    public ProfileDTO getProfile(String userName) throws NoSuchElementException,NullPointerException {
        User user=userRepository.findByEmail(userName).orElseThrow();
        return ProfileDTO.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .username(user.getUsername()).email(user.getEmail())
                .bio(user.getBio()).profileUrl(user.getProfilePicture()).followerCount(user.getFollowers().size())
                .followingCount(user.getFollowing().size()).posts(user.getPosts()).build();
    }

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

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

}
