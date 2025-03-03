package org.yavuz.post.comment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.post.exception.ResourceNotFoundException;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.comment.repository.CommentRepository;
import org.yavuz.post.post.model.Post;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment){
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
