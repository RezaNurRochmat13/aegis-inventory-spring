package com.aegis.inventory.entity.dto.user;

import jakarta.validation.constraints.NotBlank;

public class RegisterUser {
    @NotBlank String username;
    @NotBlank String password;
}
