package com.naconsulta.naconsulta.repositories;

import com.naconsulta.naconsulta.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT obj FROM Address obj " +
            "WHERE UPPER(obj.neighborhood) LIKE UPPER(CONCAT('%', :name, '%')) ")
    List<Address> searchByNeighborhood(String name);
}
