package org.yavuz.post.post.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.post.exception.ResourceNotFoundException;
import org.yavuz.post.post.model.Post;
import org.yavuz.post.post.repository.PostRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post addPost(Post post){
        return postRepository.save(post);
    }
    public Map<String, Boolean> deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı post bulunamadı."));
        Map<String, Boolean> response = new HashMap<>();
        response.put("Gönderi silindi", Boolean.TRUE);
        return response;
    }
    public Post updatePost(Long id, Post postDetails){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı post bulunamadı."));
        post.setPostText(postDetails.getPostText());
        return postRepository.save(post);
    }
    public Post getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "numaralı post bulunamadı."));
    }
}
