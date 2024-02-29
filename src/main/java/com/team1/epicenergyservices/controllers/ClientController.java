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

import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "clientId") String order) {
        return clientService.findAll(page, size, order);
    }

    @GetMapping("/{clientId}")
    public Client findById(@PathVariable UUID clientId) {
        return clientService.findById(clientId);
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
