package com.example.entity.controller;

import com.example.entity.Post;
import com.example.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")

public class PostController {

    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> creatPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
       // return ResponseEntity.created(savedPost) ;
        return new ResponseEntity<>(savedPost , HttpStatus.CREATED);
}
}
