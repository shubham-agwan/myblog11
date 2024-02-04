package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.repository.CommentRepository;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(CommentDto dto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Id not found for ID:" + postId)
        );

        Comment comment = new Comment();
        comment.setEmail(dto.getEmail());
        comment.setText(dto.getText());
        comment.setPost(post);
        
        Comment commentDto = commentRepository.save(comment);

        dto.setId(commentDto.getId());
        dto.setEmail(commentDto.getEmail());
        dto.setText(commentDto.getText());
        return dto;
    }
}
