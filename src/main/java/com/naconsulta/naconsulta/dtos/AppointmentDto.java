package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Appointment;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

public class AppointmentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @FutureOrPresent(message = "A data da consulta não pode ser passada")
    private Instant date;

    @NotBlank(message = "Campo obrigatório")
    private String diagnosis;

    @NotBlank(message = "Campo obrigatório")
    private String symptom;

    private UserMinDto user;

    private DoctorMaxAddressEspecializationDto doctor;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, Instant date, String diagnosis, String symptom, UserMinDto user, DoctorMaxAddressEspecializationDto doctor) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.symptom = symptom;
        this.user = user;
        this.doctor = doctor;
    }

    public AppointmentDto(Appointment entity) {
        id = entity.getId();
        date = entity.getDate();
        diagnosis = entity.getDiagnosis();
        symptom = entity.getSymptom();
        user = new UserMinDto(entity.getUser());
        doctor = new DoctorMaxAddressEspecializationDto(entity.getDoctor());
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public UserMinDto getUser() {
        return user;
    }

    public void setUser(UserMinDto user) {
        this.user = user;
    }

    public DoctorMaxAddressEspecializationDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorMaxAddressEspecializationDto doctor) {
        this.doctor = doctor;
    }
}