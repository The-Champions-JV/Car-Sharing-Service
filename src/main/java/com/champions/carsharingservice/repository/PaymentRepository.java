package com.champions.carsharingservice.repository;

import com.champions.carsharingservice.model.Payment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByRentalUserId(Long id, Pageable pageable);

    Optional<Payment> findBySessionId(String sessionId);

    List<Payment> findAllByRentalId(Long rentalId);
}
