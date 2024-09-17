package com.example.InstaChat.follow;

import com.example.InstaChat.follow.model.FollowDTO;
import com.example.InstaChat.follow.model.FollowRequestDTO;
import com.example.InstaChat.user.User;
import com.example.InstaChat.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    public List<FollowDTO> getFollowerList(String profileUserName, String currentUser)  {
        // Find the user for the given username
        User profileUser = userRepository.findByUserName(profileUserName)
                .orElseThrow(() -> new MessagingException("User not found with username: " + profileUserName));

        // Get the list of followers for this user
        List<User> followers = profileUser.getFollowers();

        // Convert followers to FollowDTO
        List<FollowDTO> followerDTOs = new ArrayList<>();
        for (User follower : followers) {
            boolean isFollowedByCurrentUser = userRepository.existsByUserNameAndFollowing(userRepository.findByUserName(currentUser).get().getUsername(), follower);
            followerDTOs.add(new FollowDTO(follower.getUsername(), follower.getName(), isFollowedByCurrentUser, follower.getProfilePicture()));
        }

        return followerDTOs;
    }

    public List<FollowDTO> getFollowingList(String profileUsername, String currentUser) {
        // Find the user for the given username
        User profileUser = userRepository.findByUserName(profileUsername)
                .orElseThrow(() -> new MessagingException("User not found with username: " + profileUsername));

        // Get the list of users this user is following
        List<User> following = profileUser.getFollowing();

        // Convert following list to FollowDTO
        List<FollowDTO> followingDTOs = new ArrayList<>();
        for (User followedUser : following) {
            boolean isFollowedByCurrentUser = userRepository.existsByUserNameAndFollowing(userRepository.findByUserName(currentUser).get().getUsername(), followedUser);
            followingDTOs.add(new FollowDTO(followedUser.getUsername(), followedUser.getName(), isFollowedByCurrentUser, followedUser.getProfilePicture()));
        }

        return followingDTOs;
    }

    public String followUser(FollowRequestDTO followRequest) {
        // Find the current user (follower) and the target user to be followed
        User follower = userRepository.findById(followRequest.getUserid())
                .orElseThrow(() -> new MessagingException("Current user not found"));
        User targetUser = userRepository.findByUserName(followRequest.getUsername())
                .orElseThrow(() -> new MessagingException("User not found with username: " + followRequest.getUsername()));

        // Check if the current user is already following the target user
        if (follower.getFollowing().contains(targetUser)) {
            return "Already following user: " + followRequest.getUsername();
        }

        // Add the follow relationship
        follower.getFollowing().add(targetUser);
        targetUser.getFollowers().add(follower);

        userRepository.save(follower);  // Save the follower to persist the new following
        return "Successfully followed user: " + followRequest.getUsername();
    }
}
