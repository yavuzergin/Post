package org.yavuz.post.comment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.post.comment.DTO.AddCommentRequest;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.comment.repository.CommentRepository;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;
import org.yavuz.post.post.DTO.AddPostRequest;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.repository.PostRepository;
import org.yavuz.post.post.service.PostService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private CommentService commentService;
    private static Member testMember() {
        Member member = new Member();
        member.setId(1L);
        member.setMemberFirstName("Hüseyin");
        member.setMemberLastName("Su");
        return member;
    }
    private AddCommentRequest addCommentRequest(){
       AddCommentRequest addCommentRequest = new AddCommentRequest();
       addCommentRequest.setPostId(1L);
       addCommentRequest.setMemberId(1L);
       addCommentRequest.setCommentText("Test yorum");
       addCommentRequest.setCommentDate(LocalDate.parse("2025-03-03"));
       return addCommentRequest;
    }
    private Comment testComment(){
        return new Comment(2L, testMember(), new Post(), LocalDate.parse("2025-03-03"),"test yorum v2");
    }
    @Test
    void addComment() {
        Member member = new Member();
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(commentRepository.save(any(Comment.class))).thenReturn(new Comment());
        Comment comment = commentService.addComment(addCommentRequest);
        assertNotNull(comment);
        verify(memberRepository, times(1)).findById(member.getId());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void deleteComment() {
        Comment comment = testComment();
        when(commentRepository.findById(2L)).thenReturn(Optional.of(comment));
        commentService.deleteComment(2L);
    }

    @Test
    void updateComment() {
        Comment comment = new Comment();
        Comment commentDetails = new Comment();
        commentDetails.setId(2L);
        commentDetails.setCommentText("Güncellenmiş yorum içeriği.");
        when(commentRepository.findById(2L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);
        Comment updatetComment = commentService.updateComment(2L, commentDetails);
        assertEquals("Güncellenmiş yorum içeriği.", updatetComment.getCommentText());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void getComment() {
        Comment comment = testComment();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        Comment result = commentService.getComment(1L);
        assertEquals(comment, result);
    }
}