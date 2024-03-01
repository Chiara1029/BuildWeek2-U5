//package com.team1.epicenergyservices.entities;
//
//import com.team1.epicenergyservices.services.MunicipalitiesService;
//import com.team1.epicenergyservices.services.ProvincesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Objects;
//
//@Component
//public class ProvincesAndMunicipalitiesRunner implements CommandLineRunner {
//
//    @Autowired
//    ProvincesService provincesService;
//
//    @Autowired
//    MunicipalitiesService municipalitiesService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // CREAZIONE E SALVATAGGIO DI PROVINCE
//
//        Province vco = new Province("VCO", "Verbano-Cusio-Ossola");
//        this.provincesService.save(vco);
//
//        Province su = new Province("SU", "Sud Sardegna");
//        this.provincesService.save(su);
//
//        String provinceCsv = "src/main/resources/province-italiane.csv";
//        String line;
//        String csvSplitBy = ";";
//
//
//        try (BufferedReader br = new BufferedReader(new FileReader(provinceCsv))) {
//            while ((line = br.readLine()) != null) {
//
//                String[] data = line.split(csvSplitBy);
//
//                if (!Objects.equals(data[1], "Verbania") && !Objects.equals(data[1], "Medio Campidano") && !Objects.equals(data[1], "Carbonia Iglesias") && !Objects.equals(data[1], "Ogliastra")) {
//
//                    String provinceName = data[1];
//
//                    provinceName = switch (provinceName) {
//                        case "Aosta" -> "Valle d'Aosta/Vallée d'Aoste";
//                        case "Monza-Brianza" -> "Monza e della Brianza";
//                        case "Bolzano" -> "Bolzano/Bozen";
//                        case "Reggio-Emilia" -> "Reggio nell'Emilia";
//                        case "Forli-Cesena" -> "Forlì-Cesena";
//                        case "Pesaro-Urbino" -> "Pesaro e Urbino";
//                        case "Ascoli-Piceno" -> "Ascoli Piceno";
//                        case "Reggio-Calabria" -> "Reggio Calabria";
//                        case "Vibo-Valentia" -> "Vibo Valentia";
//                        case "La-Spezia" -> "La Spezia";
//                        default -> provinceName;
//                    };
//
//                    Province province = new Province(data[0], provinceName);
//
//                    this.provincesService.save(province);
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // CREAZIONE E SALVATAGGIO DEI COMUNI
//
//        String comuniCsv = "src/main/resources/comuni-italiani.csv";
//        String line1;
//        String csvSplit = ";";
//
//
//        try (BufferedReader br = new BufferedReader(new FileReader(comuniCsv))) {
//            while ((line1 = br.readLine()) != null) {
//
//                String[] data = line1.split(csvSplit);
//
//                if (data.length >= 4) {
//
//                    String fourth = data[3];
//
//                    Municipality municipality = new Municipality(provincesService.findByName(fourth), data[2]);
//
//                    this.municipalitiesService.save(municipality);
//                } else {
//                    System.out.println("Line does not have a fourth value");
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}