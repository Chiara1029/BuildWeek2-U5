package com.team1.epicenergyservices.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceDTO(LocalDate date,
                         double invoiceValue,
                         String invoiceState,
                         UUID clientId) {
}
