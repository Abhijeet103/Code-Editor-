package com.codeEditor.v1.repository;

import com.codeEditor.v1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {


    Optional<User> findByUsername(String username);
}
