package com.codeEditor.v1.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id  ;
    @Column(unique = true)
    private String username  ;
    @Column(unique = true)
    private String email ;


    private String password  ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//  why is lombok not working

    public String getPassword()
    {
        return this.password ;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
