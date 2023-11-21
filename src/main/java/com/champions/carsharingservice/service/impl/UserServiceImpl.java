package com.champions.carsharingservice.service.impl;

import com.champions.carsharingservice.dto.UserRegistrationRequestDto;
import com.champions.carsharingservice.dto.UserResponseDto;
import com.champions.carsharingservice.exception.RegistrationException;
import com.champions.carsharingservice.mapper.UserMapper;
import com.champions.carsharingservice.model.Role;
import com.champions.carsharingservice.model.User;
import com.champions.carsharingservice.repository.RoleRepository;
import com.champions.carsharingservice.repository.UserRepository;
import com.champions.carsharingservice.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("Unable to complete registration. User already exists");
        }
        Role defaultRole = roleRepository.findRoleByName(Role.RoleName.CUSTOMER);
        User user = userMapper.toUser(requestDto);
        user.setRoles(Set.of(defaultRole));
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
