package com.team1.epicenergyservices.payload;

import java.time.LocalDate;

public record InvoiceDTO(LocalDate date,
                         Long invoiceValue,
                         Long number,
                         String invoiceState) {
}
