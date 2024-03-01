package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {

    //ORDER
    List<Client> findAllByOrderByRevenueAsc();

    List<Client> findAllByOrderByRevenueDesc();

    //FIND
    List<Client> findAllByRevenue(double revenue);

    List<Client> findAllByRegisterDate(LocalDate registerDate);

    List<Client> findAllByLastContactDate(LocalDate lastContactDate);

    List<Client> findAllByCompanyNameContainingIgnoreCase(String name);


}
