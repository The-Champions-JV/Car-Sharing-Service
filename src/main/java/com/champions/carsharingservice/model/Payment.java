package com.champions.carsharingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.net.URL;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"rental"})
@ToString(exclude = {"rental"})
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Column(nullable = false)
    private URL sessionUrl;
    @Column(nullable = false)
    private String sessionId;
    @Column(nullable = false)
    private BigDecimal amountToPay;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ?Lilja
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    private enum PaymentStatus {
        PENDING,
        PAID,
        CANCELED
    }

    private enum PaymentType {
        PAYMENT,
        FINE
    }
}
