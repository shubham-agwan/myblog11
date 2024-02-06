package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.repository.CommentRepository;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    PostRepository postRepository;

    ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
    Comment mapToEntity( CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
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

    @Override
    public void deleteCommentById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found for ID:" + id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public CommentDto updateCommentById(long id,
                                        CommentDto commentDto,
                                        long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found ID:" + postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Id not found id:"+id)
        );
        Comment comment1 = mapToEntity(commentDto); //copy commentDto -> comment

        comment1.setId(comment.getId());
        comment1.setPost(post);
        System.out.println("comment1-"+comment1.getId());
        System.out.println("comment-"+comment.getId());
        /*comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(comment.getPost());
        comment.setId(commentDto.getId());*/
        Comment save = commentRepository.save(comment1); //copy copy saved comment to new saved comment
        CommentDto commentDto1 = mapToDto(save);
        return commentDto1;
    }
}
