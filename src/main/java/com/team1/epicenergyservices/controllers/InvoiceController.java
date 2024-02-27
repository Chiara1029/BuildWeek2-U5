package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.Invoice;
import com.team1.epicenergyservices.payloads.InvoiceDTO;
import com.team1.epicenergyservices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice saveInvoice(@RequestBody InvoiceDTO newInvoice) {
        return this.invoiceService.newInvoice(newInvoice);
    }

    @GetMapping
    public Page<Invoice> getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy) {
        return this.invoiceService.getInvoice(page, size, orderBy);
    }

    @DeleteMapping("/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void findIdAndDelete(@PathVariable UUID invoiceId) {
        this.invoiceService.findByIdThenDelete(invoiceId);
    }


}
