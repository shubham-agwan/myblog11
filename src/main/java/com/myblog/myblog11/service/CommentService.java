package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto dto, Long postId );

    void deleteCommentById(long id);

    CommentDto updateCommentById(long id,
                                 CommentDto commentDto,
                                 long PostId);
}
