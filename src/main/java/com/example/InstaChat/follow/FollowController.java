package com.example.InstaChat.follow;


import com.example.InstaChat.follow.model.FollowDTO;
import com.example.InstaChat.follow.model.FollowRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    ResponseEntity<?> follow(@RequestBody FollowRequestDTO followRequest){
        String response = followService.followUser(followRequest);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/followers")
    ResponseEntity<?> getFollowers(@RequestParam("profileUser") String profileUser,@RequestParam("currentUser") String username){
        List<FollowDTO> response = followService.getFollowerList(profileUser,username);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/followings")
    ResponseEntity<?> getFollowings(@RequestParam("profileUser") String profileUser,@RequestParam("currentUser") String userName){
        List<FollowDTO> response= followService.getFollowingList(profileUser,userName);
        return ResponseEntity.ok().body(response);
    }

}

