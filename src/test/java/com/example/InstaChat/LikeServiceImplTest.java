package com.example.InstaChat;
import com.example.InstaChat.dto.LikeDTO;
import com.example.InstaChat.entities.Like;
import com.example.InstaChat.repositories.LikeRepository;
import com.example.InstaChat.services.LikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LikeServiceImplTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeServiceImpl likeService;

    @Test
    void testPostLike() {
        LikeDTO likeDTO = new LikeDTO("postId", "username", "name", true, "profileUrl");
        Like like = new Like();

        when(likeRepository.save(Mockito.any(Like.class))).thenReturn(like);

        String result = likeService.postLike(likeDTO);

        assertEquals("Like added", result);
    }
}
