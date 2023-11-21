package com.champions.carsharingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(@NotBlank
                                  @Email
                                  @Length(max = 255)
                                  String email,
                                  @NotBlank
                                  @Length(min = 8, max = 50)
                                  String password) {
}
