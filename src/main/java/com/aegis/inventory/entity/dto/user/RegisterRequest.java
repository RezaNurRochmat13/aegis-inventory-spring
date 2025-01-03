package com.aegis.inventory.entity.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String name;
    private String email;
    @NotBlank
    private String role;
}
