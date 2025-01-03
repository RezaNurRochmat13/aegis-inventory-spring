package com.aegis.inventory.presenter;

import com.aegis.inventory.entity.dto.user.LoginRequest;
import com.aegis.inventory.entity.dto.user.RegisterRequest;
import com.aegis.inventory.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationPresenter {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody RegisterRequest payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", authService.registerUser(payload));
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody LoginRequest payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", authService.loginUser(payload));
        return response;
    }
}
