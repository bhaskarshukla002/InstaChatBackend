package com.example.InstaChat.servicesinterface;

import com.example.InstaChat.dto.CommentDTO;

public interface CommentService {
    String postComment(CommentDTO commentDTO);
}
