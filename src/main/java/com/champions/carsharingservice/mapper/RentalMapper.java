package com.champions.carsharingservice.mapper;

import com.champions.carsharingservice.dto.rental.RentalDto;
import com.champions.carsharingservice.model.Rental;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface RentalMapper {
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "car.brand", target = "carBrand")
    @Mapping(source = "car.model", target = "carModel")
    RentalDto toDto(Rental rental);
}
