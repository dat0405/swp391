package com.tatdat.parking.backend.controller;

import com.tatdat.parking.backend.dto.UpdateUserRoleRequest;
import com.tatdat.parking.backend.dto.UpdateUserStatusRequest;
import com.tatdat.parking.backend.dto.UserResponse;
import com.tatdat.parking.backend.entity.Role;
import com.tatdat.parking.backend.entity.User;
import com.tatdat.parking.backend.repository.RoleRepository;
import com.tatdat.parking.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToUserResponse(user);
    }

    @PutMapping("/{id}/role")
    public UserResponse updateUserRole(
            @PathVariable Integer id,
            @RequestBody UpdateUserRoleRequest request
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    @PutMapping("/{id}/status")
    public UserResponse updateUserStatus(
            @PathVariable Integer id,
            @RequestBody UpdateUserStatusRequest request
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String status = request.getStatus();

        if (!"ACTIVE".equals(status)
                && !"INACTIVE".equals(status)
                && !"BANNED".equals(status)) {
            throw new RuntimeException("Invalid status");
        }

        user.setStatus(status);
        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .roleName(user.getRole().getRoleName())
                .build();
    }
}