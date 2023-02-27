package com.alok91340.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alok91340.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
   
    private int postId; 
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    private CategoryDto category;
    private UserDto user;
    private Set<Comment> comments=new HashSet<>();
}
