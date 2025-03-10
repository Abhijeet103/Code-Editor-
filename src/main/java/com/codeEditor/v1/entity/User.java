package com.codeEditor.v1.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id  ;
    @Column(unique = true)
    private String username  ;
    @Column(unique = true)
    private String email ;
    private String password  ;


}
