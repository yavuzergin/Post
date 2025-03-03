package org.yavuz.post.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.service.PostService;
import org.yavuz.post.post.repository.PostRepository;

import java.util.Map;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/post-api/")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get-post/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }
    @PostMapping("/add-post")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePost(@PathVariable Long id){
        Map<String, Boolean> response = postService.deletePost(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update-post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails){
        Post updatedPost = postService.updatePost(id, postDetails);
        return ResponseEntity.ok(updatedPost);
    }
}
