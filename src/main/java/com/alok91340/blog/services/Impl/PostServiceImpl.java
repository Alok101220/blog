package com.alok91340.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alok91340.blog.entities.Category;
import com.alok91340.blog.entities.Post;
import com.alok91340.blog.entities.User;
import com.alok91340.blog.excepctions.ResourceNotFoundExcepction;
import com.alok91340.blog.payloads.PostDto;
import com.alok91340.blog.payloads.PostResponse;
import com.alok91340.blog.repositories.CategoryRepo;
import com.alok91340.blog.repositories.PostRepo;
import com.alok91340.blog.repositories.UserRepo;
import com.alok91340.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundExcepction("user"," user id",userId));
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundExcepction("category", "category id", categoryId));
        
        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDateCreated(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExcepction("post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExcepction("post", "post id", postId));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pagable= PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost=this.postRepo.findAll(pagable);
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        
        postResponse.setPostDtos(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExcepction("post", "post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(
            ()-> new ResourceNotFoundExcepction("category", "category id", categoryId));
        List<Post> posts= this.postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map(
            (post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(
            ()-> new ResourceNotFoundExcepction("user", "user id", userId));
        List<Post> posts= this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map(
            (post)->this.modelMapper.map(post,PostDto.class )).collect(Collectors.toList());
        
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyWord) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyWord);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}
