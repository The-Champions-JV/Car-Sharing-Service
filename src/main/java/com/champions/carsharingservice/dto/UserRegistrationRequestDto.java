package com.champions.carsharingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRegistrationRequestDto(@NotBlank @Email @Length(max = 255) String email,
                                         @NotBlank @Length(min = 8, max = 50) String password,
                                         @NotBlank @Length(max = 50) String firstName,
                                         @NotBlank @Length(max = 50) String lastName) {
}
