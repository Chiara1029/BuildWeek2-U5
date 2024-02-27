package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.entities.Municipality;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.repositories.MunicipalitiesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipalitiesService {

    @Autowired
    private MunicipalitiesDAO municipalitiesDAO;

    public List<Municipality> getMunicipalities() {
        return municipalitiesDAO.findAll();
    }
    public void save(Municipality municipality) {
       Optional<Municipality> found = municipalitiesDAO.findByMunicipality(municipality.getMunicipality());
       found.ifPresent(foundMunicipality -> {
           if (foundMunicipality.getProvince().equals(municipality.getProvince())) {
               throw new BadRequestException(municipality.getMunicipality() + " municipality exists.");
           }
       });
        municipalitiesDAO.save(municipality);
    }

    public Municipality findByName(String provinceName) {
        return municipalitiesDAO.findByMunicipality(provinceName).orElseThrow(() -> new NotFoundException(provinceName));
    }

    public Municipality findById(int id) {
        return municipalitiesDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
