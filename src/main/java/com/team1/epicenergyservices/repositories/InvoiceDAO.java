package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceDAO extends JpaRepository<Invoice, UUID> {
    Optional<Invoice> findById(UUID id);
}
