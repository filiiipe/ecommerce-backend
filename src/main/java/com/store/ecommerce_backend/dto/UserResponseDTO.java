package com.store.ecommerce_backend.dto;

import com.store.ecommerce_backend.model.enums.Role;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        Role role
) {}