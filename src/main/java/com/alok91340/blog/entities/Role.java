package com.alok91340.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private String name;
}
