package com.alok91340.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alok91340.blog.entities.Comment;
import com.alok91340.blog.entities.Post;
import com.alok91340.blog.excepctions.ResourceNotFoundExcepction;
import com.alok91340.blog.payloads.CommentDto;
import com.alok91340.blog.repositories.CommentRepo;
import com.alok91340.blog.repositories.PostRepo;
import com.alok91340.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExcepction("post", "post id",postId));
        Comment comment=this.modelMapper.map(commentDto,Comment.class );
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundExcepction("comment", "comment id", commentId));
        this.commentRepo.delete(comment);
    }
    
}
