package com.alok91340.blog.services;

import java.util.List;

import com.alok91340.blog.payloads.PostDto;
import com.alok91340.blog.payloads.PostResponse;

public interface PostService {
    
    // create post

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update post
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete post
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    // get post by id
    PostDto getPostById(Integer postId);

    // get post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get post by user
    List<PostDto> getPostsByUser(Integer userId);

    // search posts
    List<PostDto> searchPosts(String keyWord);

}
