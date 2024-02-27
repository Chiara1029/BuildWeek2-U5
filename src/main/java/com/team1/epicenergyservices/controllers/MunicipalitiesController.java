package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.Municipality;
import com.team1.epicenergyservices.services.MunicipalitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MunicipalitiesController {
    @Autowired
    private MunicipalitiesService municipalitiesService;

    @GetMapping
    public List<Municipality> getAllMunicipalities() {
        return this.municipalitiesService.getMunicipalities();
    }

    @GetMapping("/{id}")
    public Municipality findById(@PathVariable int id) {
        return this.municipalitiesService.findById(id);
    }
}
