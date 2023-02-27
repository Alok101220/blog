package com.alok91340.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private int id;

    @NotEmpty
    private String name;

    @Email
    private String email;
    @NotEmpty
    @Size(min=8,max=12,message = "password should be of min 8 and max 12 chars!!")
    @Pattern(regexp = "^(?=.*)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Minimum eight , at least one uppercase letter, one lowercase letter, one number and one special characte")
    private String password;
    @NotNull
    private String about;
}
