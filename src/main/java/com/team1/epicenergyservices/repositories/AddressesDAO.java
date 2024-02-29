package com.team1.epicenergyservices.repositories;

import com.team1.epicenergyservices.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesDAO extends JpaRepository<Address, Integer> {
}
