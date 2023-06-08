package com.naconsulta.naconsulta.repositories;

import com.naconsulta.naconsulta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT obj FROM User obj " +
            "WHERE UPPER(obj.firstName) LIKE UPPER(CONCAT('%', :name, '%')) ")
    List<User> searchByName(String name);
}

