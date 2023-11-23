package com.champions.carsharingservice.controller;

import com.champions.carsharingservice.dto.CreatePaymentRequestDto;
import com.champions.carsharingservice.dto.PaymentDto;
import com.champions.carsharingservice.dto.rental.RentalDto;
import com.champions.carsharingservice.model.User;
import com.champions.carsharingservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/payments")
@Tag(name = "Rental management", description = "Endpoints for managing rentals")
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    @Operation(summary = "Get all rentals",
            description = "Get all rentals for user, Pageable default: page = 0, size = 10")
    public List<RentalDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                  Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return null;
    }

    @PostMapping("/pay")
    @PreAuthorize("hasRole('CUSTOMER')")
    public PaymentDto createPaymentIntent(@RequestBody @Valid CreatePaymentRequestDto requestDto) {
        return paymentService.createPaymentSession(requestDto);
    }

    @GetMapping("/success")
    public PaymentDto afterSuccessPayment(@RequestParam String sessionId) {
        return paymentService.getSuccessfulPayment(sessionId);
    }

    @GetMapping("/cancel")
    public PaymentDto afterCancelPayment(@RequestParam String sessionId) {
        return paymentService.getCancelledPayment(sessionId);
    }
}
