package com.aegis.inventory.service;

import com.aegis.inventory.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllActiveUsers();
}
