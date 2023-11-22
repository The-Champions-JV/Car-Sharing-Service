package com.champions.carsharingservice.controller;

import com.champions.carsharingservice.dto.rental.CreateRentalRequestDto;
import com.champions.carsharingservice.dto.rental.RentalDto;
import com.champions.carsharingservice.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/rentals")
@Tag(name = "Rental management", description = "Endpoints for managing rentals")
public class RentalController {
    private final RentalService rentalService;

    //user
    @PostMapping
    @Operation(summary = "Create new rental", description = """
            Create a new rental 
            Prams: carId, returnDateTime
            """)
    public RentalDto createRental(@RequestBody CreateRentalRequestDto requestDto) {
        return rentalService.createRental(requestDto);
    }

    //user
    @GetMapping
    @Operation(summary = "Get all rentals",
            description = "Get all rentals for user, Pageable default: page = 0, size = 10")
    public List<RentalDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        //User user = (User) authentication.getPrincipal();
        return rentalService.getAllRentals(1L, pageable);
    }

    //User
    @GetMapping("/")
    @Operation(summary = "Get all rentals by activeness",
            description = """
                    Get all rentals by activeness (if actual return date is null = active)
                     for user, Pageable default: page = 0, size = 10
                    """)
    public List<RentalDto> getAllRentalsByActiveness(@RequestParam(name = "is_active")
                                                     boolean isActive,
                                                     @PageableDefault(page = 0, size = 10)
                                                     Pageable pageable) {
        if (isActive) {
            return rentalService.getAllActiveRentals(1L, pageable);
        } else {
            return rentalService.getAllNotActiveRentals(1L, pageable);
        }
    }

    //Manager
    @GetMapping("/search/")
    @Operation(summary = "Search rentals for manager",
            description = """
                    Search rentals using userId and activeness, 
                    default activeness = true
                    default: page = 0, size = 10
                    """)
    public List<RentalDto> getAllRentalsByUserAndActiveness(
            @RequestParam(name = "user_id")
            Long userId,
            @RequestParam(name = "is_active", defaultValue = "true")
            boolean isActive,
            @PageableDefault(page = 0, size = 10)
            Pageable pageable) {
        if (isActive) {
            return rentalService.getAllActiveRentals(userId, pageable);
        } else {
            return rentalService.getAllNotActiveRentals(userId, pageable);
        }
    }

    //Manager
    @PostMapping("/{id}/return")
    @Operation(summary = "Return rental by id", description = """
           Return rental by setting actual return date
           if actual return date is after return date, new payment(Fine) is created 
           depending on how late the return was
           """)
    public RentalDto returnRental(@PathVariable @Positive Long id) {
        return rentalService.setActualReturnDate(1L, id);
    }
}
