package com.aegis.inventory.service;

import com.aegis.inventory.entity.User;
import com.aegis.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }
}
