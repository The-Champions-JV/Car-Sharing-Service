package com.champions.carsharingservice.service;

import com.champions.carsharingservice.dto.rental.CreateRentalRequestDto;
import com.champions.carsharingservice.dto.rental.RentalDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RentalService {
    RentalDto createRental(CreateRentalRequestDto requestDto);

    RentalDto findRentalById(Long userId, Long rentalId);

    List<RentalDto> getAllRentals(Long userId, Pageable pageable);

    List<RentalDto> getAllActiveRentals(Long userId, Pageable pageable);

    List<RentalDto> getAllNotActiveRentals(Long userId, Pageable pageable);

    RentalDto setActualReturnDate(Long userId, Long rentalId);
}
