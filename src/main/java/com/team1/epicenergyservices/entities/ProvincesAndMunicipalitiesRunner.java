package com.team1.epicenergyservices.entities;

import com.team1.epicenergyservices.services.MunicipalitiesService;
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

    @Autowired
    MunicipalitiesService municipalitiesService;

    @Override
    public void run(String... args) throws Exception {

        // CREAZIONE E SALVATAGGIO DI PROVINCE

        Province vco = new Province("VCO", "Verbano-Cusio-Ossola");
        this.provincesService.save(vco);

        Province su = new Province("SU", "Sud Sardegna");
        this.provincesService.save(su);

        String provinceCsv = "src/main/resources/province-italiane.csv";
        String line;
        String csvSplitBy = ";";


        try (BufferedReader br = new BufferedReader(new FileReader(provinceCsv))) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(csvSplitBy);

                Province province = new Province(data[0], data[1]);

                this.provincesService.save(province);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREAZIONE E SALVATAGGIO DEI COMUNI

        String comuniCsv = "src/main/resources/comuni-italiani.csv";
        String line1;
        String csvSplit = ";";


        try (BufferedReader br = new BufferedReader(new FileReader(comuniCsv))) {
            while ((line1 = br.readLine()) != null) {

                String[] data = line1.split(csvSplit);

                if (data.length >= 4) {

                    String fourth = data[3];

                    fourth = switch (fourth) {
                        case "Valle d'Aosta/Vallée d'Aoste" -> "Aosta";
                        case "Monza e della Brianza" -> "Monza-Brianza";
                        case "Bolzano/Bozen" -> "Bolzano";
                        case "Reggio nell'Emilia" -> "Reggio-Emilia";
                        case "Forlì-Cesena" -> "Forli-Cesena";
                        case "Pesaro e Urbino" -> "Pesaro-Urbino";
                        case "Ascoli Piceno" -> "Ascoli-Piceno";
                        case "Reggio Calabria" -> "Reggio-Calabria";
                        case "Vibo Valentia" -> "Vibo-Valentia";
                        case "La Spezia" -> "La-Spezia";
                        default -> fourth;
                    };

                    Municipality municipality = new Municipality(provincesService.findByName(fourth), data[2]);

                    this.municipalitiesService.save(municipality);
                } else {
                    System.out.println("Line does not have a fourth value");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}