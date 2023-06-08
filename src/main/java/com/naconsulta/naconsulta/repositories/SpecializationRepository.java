package com.naconsulta.naconsulta.repositories;

import com.naconsulta.naconsulta.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    @Query("SELECT obj FROM Specialization obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    List<Specialization> searchByName(String name);
}
