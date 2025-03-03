package org.yavuz.post.post.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;
import org.yavuz.post.post.DTO.AddPostRequest;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.repository.PostRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
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
    void addPost() {
        Member member = new Member();
        AddPostRequest addPostRequest = new AddPostRequest();
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(postRepository.save(any(Post.class))).thenReturn(new Post());
        Post post = postService.addPost(addPostRequest);
        assertNotNull(post);
        verify(memberRepository, times(1)).findById(member.getId());
        verify(postRepository, times(1)).save(any(Post.class));

    }

    @Test
    void deletePost() {
        Post post = testPost();
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        postService.deletePost(1L);
    }

    @Test
    void updatePost() {
        Post post = testPost();
        Post postDetails = new Post();
        postDetails.setId(1L);
        postDetails.setPostText("Post textimi güncelledim.");
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);
        Post updatedPost = postService.updatePost(1L, postDetails);
        assertEquals("Post textimi güncelledim.", updatedPost.getPostText());
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void getPost() {
        Post post = testPost();
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post result = postService.getPost(1L);
        assertEquals(post, result);
    }
}