package com.codeEditor.v1.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id  ;
    private String username  ;
    @Column(unique = true)
    private String email ;
    private String password  ;


}
