package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceDAO extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findById(Long id);
    List<Invoice> findByClientId(UUID clientId);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceState = :invoiceState")
    List<Invoice> findByInvoiceState(String invoiceState);
    @Query("SELECT i FROM Invoice i WHERE i.date = :date")
    List<Invoice> findByDate(LocalDate date);

    @Query("SELECT i FROM Invoice i WHERE YEAR(i.date) = :year")
    List<Invoice> findByYear(int year);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceValue BETWEEN :value1 AND :value2")
    List<Invoice> findByValueRange(double value1, double value2);


}
