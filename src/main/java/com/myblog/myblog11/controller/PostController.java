package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity <> (dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/all?pageNo=3&pageSize=9
    @GetMapping("/all")
    public List<PostDto> getAllPosts(
            @RequestParam(name = "pageNo" , required = false , defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ){
        List<PostDto> allPosts = postService.getAllPosts(pageNo,pageSize);
        System.out.println(allPosts);
        return allPosts;
    }
}
