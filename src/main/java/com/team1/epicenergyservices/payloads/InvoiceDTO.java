package com.team1.epicenergyservices.payloads;

import java.time.LocalDate;

public record InvoiceDTO(LocalDate date,
                         Long invoiceValue,
                         Long number,
                         String invoiceState) {
}
