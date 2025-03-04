package org.yavuz.post.comment.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yavuz.post.comment.DTO.AddCommentRequest;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.comment.service.CommentService;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.DTO.AddPostRequest;
import org.yavuz.post.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @Test
    void getComment() throws Exception{
        Member member = new Member(1L, "Hakan", "Haklı");
        Post mockPost = new Post(1L, member, "Test post", LocalDate.of(2025, 3, 3));
        Comment mockComment = new Comment(1L, member, mockPost, LocalDate.of(2025,3,3), "Test yorum.");
        when(commentService.getComment(1L)).thenReturn(mockComment);
        mockMvc.perform(get("/comment-api/get-comment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentText").value("Test yorum."));
    }
    @Test
    void addComment() throws Exception {
        AddCommentRequest input = new AddCommentRequest();
        input.setMemberId(1L);
        input.setPostId(1L);
        input.setCommentDate(LocalDate.of(2025,1,1));
        input.setCommentText("Test yorumu.");
        Member member = new Member(2L, "Rıza", "Su");
        Post mockPost = new Post(1L, member, "Test post", LocalDate.of(2025, 3, 3));
        Comment newComment = new Comment(1L, member, mockPost, LocalDate.of(2025,3,3), "Test yorum.");
        when(commentService.addComment(any(AddCommentRequest.class))).thenReturn(newComment);

        String requestJson = "{\"memberId\": 1, \"postId\": 1, \"commentDate\": \"2025-03-03\", \"commentText\": \"Test yorum.\"}";

        mockMvc.perform(post("/comment-api/add-comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.commentText").value("Test yorum."));
    }

    @Test
    void updateComment() throws Exception {
        Member member = new Member(2L, "Rıza", "Su");
        Post mockPost = new Post(1L, member, "Test post", LocalDate.of(2025, 3, 3));
        Comment updatedComment = new Comment(1L, member, mockPost, LocalDate.of(2025,3,3), "Güncellenmiş yorum.");
        when(commentService.updateComment(eq(1L), any(Comment.class))).thenReturn(updatedComment);
        String requestJson = "{\"commentText\": \"Güncellenmiş yorum.\"}";
        mockMvc.perform(put("/comment-api/update-comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.commentText").value("Güncellenmiş yorum."));


    }

    @Test
    void deleteComment() throws Exception {
        Map<String, Boolean> response = new HashMap<>();
        response.put("Yorum silindi", Boolean.TRUE);
        when(commentService.deleteComment(1L)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(delete("/comment-api/delete-comment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['Yorum silindi']").value(true));
    }

}