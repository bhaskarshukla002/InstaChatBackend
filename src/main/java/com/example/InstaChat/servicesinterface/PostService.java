package com.example.InstaChat.servicesinterface;

import com.example.InstaChat.dto.CommentDTO;
import com.example.InstaChat.dto.LikeDTO;
import com.example.InstaChat.dto.PostUploadDTO;

import java.util.List;

public interface PostService {
    String uploadPost(PostUploadDTO post);
    List<LikeDTO> getPostLikes(String postId,String userId);
    List<CommentDTO> getPostComments(String postId, String userId);
}
