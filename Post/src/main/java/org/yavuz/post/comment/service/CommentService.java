package org.yavuz.post.comment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.post.comment.DTO.AddCommentRequest;
import org.yavuz.post.exception.ResourceNotFoundException;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.comment.repository.CommentRepository;
import org.yavuz.post.member.model.Member;
import org.yavuz.post.member.repository.MemberRepository;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.repository.PostRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment addComment(AddCommentRequest input){
        Member member = memberRepository.findById(input.getMemberId())
                .orElseThrow(()-> new ResourceNotFoundException(input.getMemberId() + "numaralı üye bulunamadı."));
        Post post = postRepository.findById(input.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException(input.getPostId() + "numaralı post bulunamadı."));
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setPost(post);
        comment.setCommentDate(input.getCommentDate());
        comment.setCommentText(input.getCommentText());
        return commentRepository.save(comment);
    }
    public Map<String, Boolean> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı yorum bulunamadı."));
        Map<String, Boolean> response = new HashMap<>();
        response.put("Yorum silindi", Boolean.TRUE);
        return response;
    }
    public Comment updateComment(Long id, Comment commentDetails){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı yorum bulunamadı."));
        comment.setCommentText(commentDetails.getCommentText());
        return commentRepository.save(comment);
    }
    public Comment getComment(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı yorum bulunamadı."));
    }
}
