package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipalitiesDAO extends JpaRepository<Municipality, Integer> {
    Optional<Municipality> findByMunicipality(String municipality);
}
