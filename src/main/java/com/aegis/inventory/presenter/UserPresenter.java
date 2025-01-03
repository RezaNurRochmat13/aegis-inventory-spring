package com.aegis.inventory.presenter;

import com.aegis.inventory.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserPresenter {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public Map<String, Object> getUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.findAllActiveUsers());
        return response;
    }
}
