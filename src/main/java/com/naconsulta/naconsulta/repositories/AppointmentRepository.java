package com.naconsulta.naconsulta.repositories;

import com.naconsulta.naconsulta.entities.Appointment;
import com.naconsulta.naconsulta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);

    @Query("SELECT obj FROM Appointment obj "
            + "WHERE obj.date >= :min "
            + "AND obj.date <= :max "
            + "AND UPPER(obj.doctor.firstName) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Appointment> searchAppointments(Instant min, Instant max, String name);
}
