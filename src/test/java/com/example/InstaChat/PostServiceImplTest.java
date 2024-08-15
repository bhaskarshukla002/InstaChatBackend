package com.example.InstaChat;

import com.example.InstaChat.dto.PostUploadDTO;
import com.example.InstaChat.entities.Post;
import com.example.InstaChat.repositories.PostRepository;
import com.example.InstaChat.services.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void testUploadPost() {
        PostUploadDTO postUploadDTO = new PostUploadDTO("username", "url", "caption");
        Post post = new Post();
        post.setContentUrl(postUploadDTO.getPostContentUrl());
        post.setCaption(postUploadDTO.getCaption());

        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        String result = postService.uploadPost(postUploadDTO);

        assertEquals("Post uploaded successfully", result);
    }

    @Test
    void testGetPostLikes() {
        // Mock the response for likes
    }

    @Test
    void testGetPostComments() {
        // Mock the response for comments
    }
}
