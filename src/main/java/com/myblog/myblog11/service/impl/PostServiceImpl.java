package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        Post savePost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setContent(savePost.getContent());
        dto.setDescription(savePost.getDescription());
        dto.setTitle(savePost.getTitle());
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found id:"+id)
                );

        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

}
