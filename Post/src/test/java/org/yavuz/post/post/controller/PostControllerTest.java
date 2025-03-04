package org.yavuz.post.post.controller;
import org.yavuz.post.exception.ResourceNotFoundException;
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

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    // GET /get-post/{id} testi
    @Test
    public void testGetPost() throws Exception {
        // Arrange
        Member member = new Member(1L, "John", "Doe");
        Post mockPost = new Post(1L, member, "Test post", LocalDate.of(2023, 10, 1));
        when(postService.getPost(1L)).thenReturn(mockPost);

        // Act & Assert
        mockMvc.perform(get("/post-api/get-post/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.postText").value("Test post"))
                .andExpect(jsonPath("$.postDate").value("2023-10-01"))
                .andExpect(jsonPath("$.member.id").value(1L))
                .andExpect(jsonPath("$.member.memberFirstName").value("John"))
                .andExpect(jsonPath("$.member.memberLastName").value("Doe"));
    }

    // POST /add-post testi
    @Test
    public void testAddPost() throws Exception {
        // Arrange
        AddPostRequest input = new AddPostRequest();
        input.setMemberId(1L);
        input.setPostText("New post");
        input.setPostDate(LocalDate.of(2023, 10, 2));

        Member member = new Member(1L, "John", "Doe");
        Post savedPost = new Post(1L, member, "New post", LocalDate.of(2023, 10, 2));
        when(postService.addPost(any(AddPostRequest.class))).thenReturn(savedPost);

        String requestJson = "{\"memberId\": 1, \"postText\": \"New post\", \"postDate\": \"2023-10-02\"}";

        // Act & Assert
        mockMvc.perform(post("/post-api/add-post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.postText").value("New post"))
                .andExpect(jsonPath("$.postDate").value("2023-10-02"))
                .andExpect(jsonPath("$.member.id").value(1L))
                .andExpect(jsonPath("$.member.memberFirstName").value("John"))
                .andExpect(jsonPath("$.member.memberLastName").value("Doe"));
    }

    // DELETE /delete-post/{id} testi
    @Test
    public void testDeletePost() throws Exception {
        // Arrange
        Map<String, Boolean> response = new HashMap<>();
        response.put("Gönderi silindi", Boolean.TRUE);
        when(postService.deletePost(1L)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(delete("/post-api/delete-post/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['Gönderi silindi']").value(true));
    }

    // PUT /update-post/{id} testi
    @Test
    public void testUpdatePost() throws Exception {
        // Arrange
        Member member = new Member(1L, "John", "Doe");
        Post updatedPost = new Post(1L, member, "Updated post", LocalDate.of(2023, 10, 1));
        when(postService.updatePost(eq(1L), any(Post.class))).thenReturn(updatedPost);

        String requestJson = "{\"postText\": \"Updated post\"}";

        // Act & Assert
        mockMvc.perform(put("/post-api/update-post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.postText").value("Updated post"))
                .andExpect(jsonPath("$.postDate").value("2023-10-01"))
                .andExpect(jsonPath("$.member.id").value(1L))
                .andExpect(jsonPath("$.member.memberFirstName").value("John"))
                .andExpect(jsonPath("$.member.memberLastName").value("Doe"));
    }
}
/* import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.controller.PostController;
import org.yavuz.post.post.DTO.AddPostRequest;
import org.yavuz.post.post.service.PostService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.service.PostService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PostService postService;

    private static Member testMember() {
        Member member = new Member();
        member.setId(1L);
        member.setMemberFirstName("Hüseyin");
        member.setMemberLastName("Su");
        return member;
    }

    private static Post testPost() {
        return new Post(1L, new Member(1L, "Hüseyin", "Su"), "Test post text",
                LocalDate.parse("2025-01-01"));
    }


    private static AddPostRequest addPostRequest() {
        AddPostRequest addPostRequest = new AddPostRequest();
        addPostRequest.setMemberId(1L);
        addPostRequest.setPostDate(LocalDate.parse("2025-01-01"));
        addPostRequest.setPostText("Test ediyorum post-text1");
        return addPostRequest;
    }


    @Test
    void getPostTest() throws Exception {
        when(postService.getPost(testPost().getId())).thenReturn(testPost());
        ResultActions resultActions = mockMvc.perform(get("/post-api/get-post/{id}", testPost().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postText").value("Test post text"));
        verify(postService, times(1)).getPost(testPost().getId());
    }

    @Test
    void addPostTest() throws Exception {
        when(postService.addPost(Mockito.any(AddPostRequest.class))).thenReturn(testPost());

    }
}

 */