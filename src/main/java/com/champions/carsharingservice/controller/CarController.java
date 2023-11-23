package com.champions.carsharingservice.controller;

import com.champions.carsharingservice.dto.car.CarDto;
import com.champions.carsharingservice.dto.car.CreateCarRequestDto;
import com.champions.carsharingservice.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Car management", description = "Endpoints for managing cars")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new car", description = "Add a new car")
    public CarDto add(@RequestBody @Valid CreateCarRequestDto request) {
        return carService.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search cars",
            description = "Get list of cars with specified parameters")
    public List<CarDto> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a car", description = "Get a car by id, if this id exists")
    public CarDto getById(@PathVariable Long id) {
        return carService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a car", description = "Update a car by id, if this id exists")
    public CarDto update(@PathVariable Long id, @RequestBody @Valid CreateCarRequestDto request) {
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a car", description = "Delete a car by id, if this id exists")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
