package com.champions.carsharingservice.controller;

import com.champions.carsharingservice.dto.car.CarDto;
import com.champions.carsharingservice.dto.car.CreateCarRequestDto;
import com.champions.carsharingservice.service.CarService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto add(@RequestBody @Valid CreateCarRequestDto request) {
        return carService.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto getById(@PathVariable Long id) {
        return carService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto update(@PathVariable Long id, @RequestBody @Valid CreateCarRequestDto request) {
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
