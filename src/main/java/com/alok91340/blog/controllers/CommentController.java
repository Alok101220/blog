package com.alok91340.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alok91340.blog.payloads.ApiResponse;
import com.alok91340.blog.payloads.CommentDto;
import com.alok91340.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    

    // create comment
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createdCommentDto=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED); 
    }

    // delete comment
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new  ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully !!",true),HttpStatus.OK);
    }
}

