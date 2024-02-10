package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    CommentService commentService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @RequestParam long post_id ){
        CommentDto comment = commentService.createComment(commentDto, post_id);

        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/comment/52
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<String>("Comment is deleted", HttpStatus.CREATED);
    }

    //http://localhost:8080/api/comment/id/1/post/1
    @PutMapping("/id/{id}/post/{post_id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,
                                                    @RequestBody CommentDto commentDto,
                                                    @PathVariable long post_id){
        System.out.println("inside 0");
        CommentDto commentDto1 = commentService.updateCommentById(id, commentDto, post_id);
        return new ResponseEntity<CommentDto> (commentDto1, HttpStatus.ACCEPTED);
    }

}
