package com.example.InstaChat;

import com.example.InstaChat.dto.CommentDTO;
import com.example.InstaChat.entities.Comment;
import com.example.InstaChat.repositories.CommentRepository;
import com.example.InstaChat.services.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void testPostComment() {
        CommentDTO commentDTO = new CommentDTO("postId", "username", "name", "content", true, "profileUrl");
        Comment comment = new Comment();

        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        String result = commentService.postComment(commentDTO);

        assertEquals("Comment added", result);
    }
}
