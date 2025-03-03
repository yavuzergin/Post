package org.yavuz.post.comment.controller;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.post.comment.model.Comment;
import org.yavuz.post.comment.service.CommentService;
import org.yavuz.post.comment.repository.CommentRepository;

import java.util.Map;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/comment-api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get-comment/{id}")
    public Comment getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }
    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
    @PutMapping("/update-comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails){
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteComment(@PathVariable Long id) {
        Map<String, Boolean> response = commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }
}
