package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.entities.Address;
import com.team1.epicenergyservices.payloads.AddressDTO;
import com.team1.epicenergyservices.repositories.AddressesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {

    @Autowired
    private AddressesDAO addressesDAO;

    @Autowired
    private MunicipalitiesService municipalitiesService;

    public Address save(AddressDTO addressDTO) {
        Address newAddress = new Address(addressDTO.street(), addressDTO.civicNumber(),
                addressDTO.location(), addressDTO.cap(), municipalitiesService.findByName(addressDTO.municipality()));
        return addressesDAO.save(newAddress);
    }
}
