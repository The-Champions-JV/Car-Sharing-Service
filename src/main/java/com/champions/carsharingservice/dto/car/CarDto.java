package com.champions.carsharingservice.dto.car;

import java.math.BigDecimal;

public record CarDto(
        Long id,
        String model,
        String brand,
        String type,
        int amountAvailable,
        BigDecimal dailyFee) {
}

