package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {

    List<Client> findAllByOrderByCompanyName();


}
