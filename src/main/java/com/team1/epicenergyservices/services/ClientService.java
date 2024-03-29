package com.team1.epicenergyservices.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.team1.epicenergyservices.entities.Client;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.payloads.ClientUpdateDTO;
import com.team1.epicenergyservices.payloads.NewClientDTO;
import com.team1.epicenergyservices.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private Cloudinary cloudinary;

    public Page<Client> findAll(int size, int page, String order) {
        Pageable pageable = PageRequest.of(size, page, Sort.by(order));
        return clientDAO.findAll(pageable);
    }

    public Client findById(UUID clientId) {
        return clientDAO.findById(clientId).orElseThrow(() -> new NotFoundException(clientId));
    }

    public Client saveClient(NewClientDTO body) {

        Client newClient = new Client();
        newClient.setCompanyType(body.companyType());
        newClient.setCompanyName(body.companyName());
        newClient.setPIva(body.pIva());
        newClient.setEmail(body.email());
        newClient.setLastContactDate(body.lastContactDate());
        newClient.setRevenue(body.revenue());
        newClient.setPec(body.pec());
        newClient.setTelephone(body.telephone());
        newClient.setEmailContact(body.emailContact());
        newClient.setNameContact(body.nameContact());
        newClient.setSurnameContact(body.surnameContact());
        newClient.setNumberContact(body.numberContact());
        newClient.setLogo(body.logo());
        newClient.setRegisterDate(LocalDate.now());
        newClient.setLegalAddress(addressesService.save(body.legalAddress()));
        if (body.operatingAddress() != null)
            newClient.setOperatingAddress(addressesService.save(body.operatingAddress()));
        return clientDAO.save(newClient);
    }


    public Client findByIdAndUpdate(UUID clientId, ClientUpdateDTO body) {

        Client updateClient = this.findById(clientId);

        updateClient.setCompanyType(body.clientType());
        updateClient.setPIva(body.pIva());
        updateClient.setEmail(body.email());
        updateClient.setLastContactDate(body.lastContactDate());
        updateClient.setRevenue(body.revenue());
        updateClient.setPec(body.pec());
        updateClient.setTelephone(body.telephone());
        updateClient.setEmailContact(body.contatcEmail());
        updateClient.setNameContact(body.contactName());
        updateClient.setSurnameContact(body.contactSurname());
        updateClient.setNumberContact(body.contactNumber());
        updateClient.setLogo(body.logo());

        return clientDAO.save(updateClient);

    }

    public void deleteClient(UUID id) {
        Client found = this.findById(id);
        clientDAO.delete(found);
    }

    public String uploadImage(MultipartFile file, UUID clientId) throws IOException {
        Client found = this.findById(clientId);
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setLogo(url);
        clientDAO.save(found);
        return url;
    }


    //ordino per fatturato
    public List<Client> getAllClientsOrderedByRevenueAsc() {
        return clientDAO.findAllByOrderByRevenueAsc();
    }

    public List<Client> getAllClientsOrderedByRevenueDesc() {
        return clientDAO.findAllByOrderByRevenueDesc();
    }


    //FILTRO
    public List<Client> getByRevenue(double revenue) {
        return clientDAO.findAllByRevenue(revenue);
    }

    public List<Client> getByRegisterDate(LocalDate registerDate) {
        return clientDAO.findAllByRegisterDate(registerDate);
    }

    public List<Client> getByLastContactDate(LocalDate lastContactDate) {
        return clientDAO.findAllByLastContactDate(lastContactDate);
    }

    public List<Client> getClientsByCompanyNameContaining(String name) {
        return clientDAO.findAllByCompanyNameContainingIgnoreCase(name);
    }
}
