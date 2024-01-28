package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts/
    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity <> (dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts?id=1
    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
    }
}
