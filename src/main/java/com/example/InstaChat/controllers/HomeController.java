package com.example.InstaChat.controllers;

import com.example.InstaChat.services.UserServiceImpl;
import com.example.InstaChat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public List<User> getUser(){
        System.out.println("getting users");
        return this.userServiceImpl.getUsers();
    }
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();

    }
}
