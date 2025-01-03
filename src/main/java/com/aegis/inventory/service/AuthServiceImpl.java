package com.aegis.inventory.service;

import com.aegis.inventory.entity.User;
import com.aegis.inventory.entity.dto.user.LoginRequest;
import com.aegis.inventory.entity.dto.user.RegisterRequest;
import com.aegis.inventory.exception.ResourceNotFound;
import com.aegis.inventory.exception.UserAlreadyExist;
import com.aegis.inventory.repository.UserRepository;
import com.aegis.inventory.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(RegisterRequest payload) {
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new UserAlreadyExist("User already exist");
        }

        User user = new User();
        user.setName(payload.getName());
        user.setUsername(payload.getUsername());
        user.setEmail(payload.getEmail());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setRole(payload.getRole());
        user.setIsActive(false);

        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginRequest payload) {
        User user = userRepository.findByUsername(payload.getUsername())
                .orElseThrow(() -> new ResourceNotFound("User not found with username " + payload.getUsername()));
        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
