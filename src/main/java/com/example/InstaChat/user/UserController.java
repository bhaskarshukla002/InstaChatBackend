package com.example.InstaChat.user;

import com.example.InstaChat.user.model.ChangePasswordRequest;
import com.example.InstaChat.user.model.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value="/user", produces = "application/json")
    ResponseEntity<?> getUserProfile(@RequestParam(name = "username") String username){
        try {
            ProfileDTO profile = userService.getProfile(username);
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
        String response= userService.editProfile(username,userNewDetail);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/user")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
