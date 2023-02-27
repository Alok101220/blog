package com.alok91340.blog.excepctions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundExcepction extends RuntimeException {

    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundExcepction(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s:%s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
