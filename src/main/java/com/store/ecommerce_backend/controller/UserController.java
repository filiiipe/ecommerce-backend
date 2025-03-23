package com.store.ecommerce_backend.controller;

import com.store.ecommerce_backend.dto.UserDTO;
import com.store.ecommerce_backend.dto.UserResponseDTO;
import com.store.ecommerce_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseDTO newUser = userService.createUser(userDTO);
        return ResponseEntity.status(201).body(newUser);
    }
}
