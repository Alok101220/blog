package com.alok91340.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
