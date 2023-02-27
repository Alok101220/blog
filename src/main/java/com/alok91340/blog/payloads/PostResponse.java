package com.alok91340.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

    private List<PostDto> postDtos;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private Boolean lastPage;
    
}
