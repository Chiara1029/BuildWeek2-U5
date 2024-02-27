package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.entities.Province;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.repositories.ProvincesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesDAO provincesDAO;

    public List<Province> getProvinces() {
        return provincesDAO.findAll();
    }
    public void save(Province province) {
        provincesDAO.findByProvinceName(province.getProvinceName()).ifPresent(newProvince -> {
            throw new BadRequestException(newProvince.getProvinceName() + " province exists.");
        });
        provincesDAO.save(province);
    }

    public Province findByName(String provinceName) {
       return provincesDAO.findByProvinceName(provinceName).orElseThrow(() -> new NotFoundException(provinceName));
    }

    public Province findById(int id) {
        return provincesDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
