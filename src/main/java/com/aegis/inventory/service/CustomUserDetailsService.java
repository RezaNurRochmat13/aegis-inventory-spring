package com.aegis.inventory.service;

import com.aegis.inventory.entity.User;
import com.aegis.inventory.entity.dto.user.CustomUserDetails;
import com.aegis.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for user: " + username);  // Log the username being searched
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOpt.get();
        System.out.println("User found: " + user.getUsername());
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getRole());
    }
}
