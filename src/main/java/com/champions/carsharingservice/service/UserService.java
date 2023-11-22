package com.champions.carsharingservice.service;

import com.champions.carsharingservice.dto.UserRegistrationRequestDto;
import com.champions.carsharingservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
