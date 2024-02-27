package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvincesDAO extends JpaRepository<Province, Integer> {
    Optional<Province> findByProvinceName(String name);
}
