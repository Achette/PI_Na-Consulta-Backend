package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Appointment;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

public class AppointmentMinDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @FutureOrPresent(message = "A data da consulta não pode ser passada")
    private Instant date;

    @NotNull(message = "Campo requerido")
    private Long userId;

    @NotNull(message = "Campo requerido")
    private Long doctorId;

    public AppointmentMinDto() {
    }

    public AppointmentMinDto(Long id, Instant date, Long userId, Long doctorId) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.doctorId = doctorId;
    }

    public AppointmentMinDto(Appointment entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.userId = entity.getUser().getId();
        this.doctorId = entity.getDoctor().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}