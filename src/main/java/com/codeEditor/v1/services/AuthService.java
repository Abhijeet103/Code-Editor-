package com.codeEditor.v1.services;





import com.codeEditor.v1.entity.User;
import com.codeEditor.v1.repository.UserRepository;
import com.codeEditor.v1.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  JwtUtils jwtUtils;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

//    public AuthService(UserRepository userRepository, JwtUtils jwtUtils) {
//        this.userRepository = userRepository;
//        this.jwtUtils = jwtUtils;
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }

    // Register a new user
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login and generate token
    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtils.generateToken(username);
        }
        throw new RuntimeException("Invalid username or password");
    }
}
