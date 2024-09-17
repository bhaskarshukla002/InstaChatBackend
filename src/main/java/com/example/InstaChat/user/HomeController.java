package com.example.InstaChat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home/user")
    public List<User> getUser(){
        System.out.println("getting users");
        return this.userService.getUsers();
    }
    @GetMapping("/home/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();

    }

    @RequestMapping("/")
    public String getLoggedInUser(){
        return "index";
    }

}
