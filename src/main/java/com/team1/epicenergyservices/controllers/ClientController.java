package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.Client;
import com.team1.epicenergyservices.payloads.ClientUpdateDTO;
import com.team1.epicenergyservices.payloads.NewClientDTO;
import com.team1.epicenergyservices.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String order) {
        return clientService.findAll(page, size, order);
    }

    @GetMapping("/findById/{clientId}")
    public Client findById(@PathVariable UUID clientId) {
        return clientService.findById(clientId);
    }


    //ORDERS BY
    //ordino per fatturato dec e cre
    @GetMapping("/orderByRevenue/{order}")
    public List<Client> getClientsOrderedByRevenue(@PathVariable String order) {
        if (order.equals("low")) {
            return clientService.getAllClientsOrderedByRevenueAsc();
        } else if (order.equals("plus")) {
            return clientService.getAllClientsOrderedByRevenueDesc();
        } else {
            return null;
        }
    }

    //FILTER
    @GetMapping("/revenue/{revenue}")
    public List<Client> getClientsWithRevenueGreaterThan(@PathVariable double revenue) {
        return clientService.getByRevenue(revenue);
    }

    @GetMapping("/ByRegisterDate")
    public List<Client> getClientsByRegisterDate(@RequestParam LocalDate registerDate) {
        return clientService.getByRegisterDate(registerDate);
    }

    @GetMapping("/nameContaining/{name}")
    public List<Client> getClientsByCompanyNameContaining(@PathVariable String name) {
        return clientService.getClientsByCompanyNameContaining(name);
    }

    @GetMapping("/ByLastContactDate")
    public List<Client> getClientsByLastContactDate(@RequestParam LocalDate lastContactDate) {
        return clientService.getByLastContactDate(lastContactDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createNewClient(@RequestBody NewClientDTO payload) {
        return clientService.saveClient(payload);
    }

    @PutMapping("/{clientId}")
    public Client clientUpdate(@PathVariable UUID clientId, @RequestBody ClientUpdateDTO updateBody) {
        return clientService.findByIdAndUpdate(clientId, updateBody);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClients(@PathVariable UUID clientId) {
        clientService.deleteClient(clientId);
    }

    @PatchMapping("/{clientId}/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadLogo(@RequestParam("image") MultipartFile file, @PathVariable UUID clientId) throws Exception {
        return clientService.uploadImage(file, clientId);
    }


}
