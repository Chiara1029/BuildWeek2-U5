package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.Invoice;
import com.team1.epicenergyservices.payloads.InvoiceDTO;
import com.team1.epicenergyservices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice saveInvoice(@RequestBody InvoiceDTO newInvoice) {
        return this.invoiceService.newInvoice(newInvoice);
    }

    @GetMapping
    public Page<Invoice> getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy) {
        return this.invoiceService.getInvoice(page, size, orderBy);
    }

    @GetMapping("/findByClient/{clientId}")
    public List<Invoice> findByClientId(@PathVariable UUID clientId){
        return invoiceService.findByClientId(clientId);
    }

    @GetMapping("/findByYear/{year}")
    public List<Invoice> findByYear(@PathVariable int year){
        return invoiceService.findByYear(year);
    }

    @GetMapping("/findByDate/{date}")
    public List<Invoice> findByDate(@PathVariable LocalDate date){
        return invoiceService.findByDate(date);
    }

    @GetMapping("/findByValueRange")
    public List<Invoice> findByValueRange(@RequestParam double value1,
                                          @RequestParam double value2){
        return invoiceService.findByValueRange(value1, value2);
    }

    @GetMapping("/findByState")
    public List<Invoice> findByInvoiceState(@RequestParam String invoiceState){
        return invoiceService.findByInvoiceState(invoiceState);
    }

    @DeleteMapping("/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findIdAndDelete(@PathVariable Long invoiceId) {
        this.invoiceService.findByIdThenDelete(invoiceId);
    }
}
