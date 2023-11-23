package com.champions.carsharingservice.controller;

import com.champions.carsharingservice.dto.UserInfoResponseDto;
import com.champions.carsharingservice.dto.UserResponseDto;
import com.champions.carsharingservice.dto.UserUpdateRequestDto;
import com.champions.carsharingservice.dto.UserUpdateRoleRequestDto;
import com.champions.carsharingservice.model.User;
import com.champions.carsharingservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User Controller",
        description = "Endpoints for update and get")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('MANAGER')")
    public UserResponseDto updateUserRole(@PathVariable Long id,
                                          @RequestBody UserUpdateRoleRequestDto newRole) {
        return userService.updateUserRole(id, newRole.role());
    }

    @GetMapping("/me")
    @Operation(summary = "Get a user by id", description = "Get a user by id")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UserInfoResponseDto getUserInfo(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return userService.getUserInfo(principal.getId());
    }

    @PatchMapping("/update")
    @Operation(summary = "Update User", description = "Update users firstName and lastName by id")
    @PreAuthorize("hasRole('MANAGER')")
    public UserInfoResponseDto updateUserById(Authentication authentication,
                                               @RequestBody UserUpdateRequestDto requestDto) {
        User principal = (User) authentication.getPrincipal();
        return userService.updateUserById(principal.getId(), requestDto);
    }
}
