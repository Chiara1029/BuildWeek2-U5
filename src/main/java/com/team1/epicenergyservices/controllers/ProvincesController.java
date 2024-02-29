package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.Province;
import com.team1.epicenergyservices.services.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvincesController {
    @Autowired
    private ProvincesService provincesService;

    @GetMapping
    public List<Province> getAllProvinces() {
        return this.provincesService.getProvinces();
    }

    @GetMapping("/{id}")
    public Province findById(@PathVariable int id) {
        return this.provincesService.findById(id);
    }

}
