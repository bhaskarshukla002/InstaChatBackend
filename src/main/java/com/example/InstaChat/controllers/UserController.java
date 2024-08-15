package com.example.InstaChat.controllers;

import com.example.InstaChat.dto.ProfileDTO;
import com.example.InstaChat.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value="/user", produces = "application/json")
    ResponseEntity<?> getUserProfile(@RequestParam(name = "username") String username){
        try {
            ProfileDTO profile = userServiceImpl.getProfile(username);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("error finding user");
        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("error creating dto");
        }
    }

    @PutMapping("/user")
    ResponseEntity<?> editUserDetails(@RequestBody ProfileDTO userNewDetail,@RequestParam(name = "username") String username){
        String response= userServiceImpl.editProfile(username,userNewDetail);
        return ResponseEntity.ok().body(response);
    }


}
