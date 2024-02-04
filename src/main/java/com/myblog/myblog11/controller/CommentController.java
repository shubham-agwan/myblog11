package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @RequestParam long post_id ){
        CommentDto comment = commentService.createComment(commentDto, post_id);

        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }
}
