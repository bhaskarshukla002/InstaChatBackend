package com.example.InstaChat.servicesinterface;

import com.example.InstaChat.dto.ProfileDTO;

public interface UserService {
    ProfileDTO getProfile(String userName);
    String editProfile(String userName, ProfileDTO newDetails);

}
