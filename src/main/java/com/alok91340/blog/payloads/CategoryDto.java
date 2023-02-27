package com.alok91340.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    
    private int categoryId;
    @NotEmpty
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
}
