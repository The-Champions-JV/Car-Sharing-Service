package com.champions.carsharingservice.service.impl;

import com.champions.carsharingservice.model.Car;
import com.champions.carsharingservice.model.Payment;
import com.champions.carsharingservice.model.Rental;
import com.champions.carsharingservice.service.NotificationService;
import com.champions.carsharingservice.telegram.CarSharingBot;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final CarSharingBot carSharingBot;

    @Override
    public void sendMessageAboutCreatedRental(Rental rental) {
        String carInfo = formatCarInfo(rental.getCar());
        String dateInfo = formatDate(rental.getRentalDateTime(), rental.getReturnDateTime());
        String name = "Dear, %s".formatted(rental.getUser().getFirstName());
        String message = name + System.lineSeparator()
                + "You rent car: " + carInfo + System.lineSeparator()
                + dateInfo;
        carSharingBot.sendMessage(message);
    }

    @Override
    public void sendMessageAboutOverdueRental(Rental rental) {
        Car car = rental.getCar();
        String message = """
                You should have returned car: %s,
                on %s
                """.formatted(formatCarInfo(car),
                formatDateForOverdueRental(rental.getReturnDateTime(),
                        rental.getActualReturnDateTime()));
        carSharingBot.sendMessage(message);
    }

    @Override
    public void sendMessageAboutSuccessPayment(Payment payment, Car car) {
        String message = """
                You successfully paid for car: %s,
                sum of payment: %s
                """.formatted(formatCarInfo(car), payment.getAmountToPay());
        carSharingBot.sendMessage(message);
    }

    @Override
    public void sendMessageAboutCanceledPayment(Payment payment, Car car) {
        String message = """
                Oops, something went wrong..
                Your payment for car: %s
                is failed. Try again.
                
                You should pay: %sUSD
                """.formatted(formatCarInfo(car),
                                payment.getAmountToPay());
        carSharingBot.sendMessage(message);
    }

    @Override
    public void sendScheduledMessageAboutOverdueRentals(Set<Rental> overdueRentals) {
        String message = """
                You have overdue rentals. Don't forget to return:
                %s
                """.formatted(formatRentals(overdueRentals));
        carSharingBot.sendMessage(message);
    }

    @Override
    public void sendNoRentalsOverdueMessage() {
        String message = "No rentals overdue today!";
        carSharingBot.sendMessage(message);
    }

    public String formatRentals(Set<Rental> overdueRentals) {
        StringBuilder sb = new StringBuilder();
        for (Rental rental : overdueRentals) {
            sb.append("Car: ")
                    .append(formatCarInfo(rental.getCar()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    private String formatDateForOverdueRental(LocalDateTime returnDateTime,
                                              LocalDateTime actualReturnDateTime) {
        String formattedReturnDateTime = returnDateTime.format(formatter);
        String formattedActualReturnDateTime = actualReturnDateTime.format(formatter);
        return """
                %s,
                but you return it on %s
                """.formatted(formattedReturnDateTime, formattedActualReturnDateTime);
    }

    public String formatCarInfo(Car car) {
        return "model - %s, brand - %s, type - %s"
                .formatted(car.getModel(), car.getBrand(), car.getType().name());

    }

    private String formatDate(LocalDateTime rentalDateTime, LocalDateTime returnDateTime) {
        String formattedRentalDateTime = rentalDateTime.format(formatter);
        String formattedReturnDateTime = returnDateTime.format(formatter);
        return """
                Your rent day and time is: %s,
                you should return car on %s.
                If you don't return the car on time, you will be fined.
                """.formatted(formattedRentalDateTime, formattedReturnDateTime);
    }
}
