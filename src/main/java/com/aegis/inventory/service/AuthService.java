package com.aegis.inventory.service;

import com.aegis.inventory.entity.User;
import com.aegis.inventory.entity.dto.user.LoginRequest;
import com.aegis.inventory.entity.dto.user.RegisterRequest;

public interface AuthService {
    User registerUser(RegisterRequest payload);
    String loginUser(LoginRequest payload);
}
