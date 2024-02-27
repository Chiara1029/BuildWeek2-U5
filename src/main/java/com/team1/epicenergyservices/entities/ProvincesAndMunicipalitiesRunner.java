package com.team1.epicenergyservices.entities;

import com.team1.epicenergyservices.services.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class ProvincesAndMunicipalitiesRunner implements CommandLineRunner {

    @Autowired
    ProvincesService provincesService;
    @Override
    public void run(String... args) throws Exception {

        String csvFile = "src/main/resources/province-italiane.csv";
        String line;
        String csvSplitBy = ";";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(csvSplitBy);

                Province province = new Province(data[0], data[1]);

                this.provincesService.save(province);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}