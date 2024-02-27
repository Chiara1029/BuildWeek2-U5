package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.entities.Invoice;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.payload.InvoiceDTO;
import com.team1.epicenergyservices.repositories.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    public Page<Invoice> getInvoice(int pageNum, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by(orderBy));
        return invoiceDAO.findAll(pageable);
    }

    public Invoice newInvoice(InvoiceDTO payload) {
        Invoice newInvoice = new Invoice(
                payload.date(),
                payload.number(),
                payload.invoiceValue(),
                payload.invoiceState()

        );
        return invoiceDAO.save(newInvoice);
    }

    public Invoice findById(UUID invoiceId) {
        return invoiceDAO.findById(invoiceId).orElseThrow(() -> new NotFoundException("Id " + invoiceId + " has no matches."));
    }

    public void findByIdThenDelete(UUID invoiceId) {
        Invoice idFound = this.findById(invoiceId);
        invoiceDAO.delete(idFound);
    }


    public Invoice findByIdThenUpdate(UUID invId, Invoice modifiedInvoice) {
        Invoice idFound = this.findById(invId);
        idFound.setDate(modifiedInvoice.getDate());
        idFound.setNumber(modifiedInvoice.getNumber());
        idFound.setInvoiceValue(modifiedInvoice.getInvoiceValue());
        idFound.setInvoiceState(modifiedInvoice.getInvoiceState());
        return invoiceDAO.save(idFound);
    }
}
