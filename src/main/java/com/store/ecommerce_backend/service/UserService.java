package com.store.ecommerce_backend.service;

import com.store.ecommerce_backend.dto.UserDTO;
import com.store.ecommerce_backend.dto.UserResponseDTO;
import com.store.ecommerce_backend.model.User;
import com.store.ecommerce_backend.model.enums.Role;
import com.store.ecommerce_backend.repository.UserRepository;
import com.store.ecommerce_backend.exception.EmailAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email já cadastrado!");
        }

        User user = User.builder()
                .name(userDTO.name())
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .role(userDTO.role() != null ? userDTO.role() : Role.USER)
                .build();

        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
