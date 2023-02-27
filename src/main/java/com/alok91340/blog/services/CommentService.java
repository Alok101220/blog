package com.alok91340.blog.services;

import com.alok91340.blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
