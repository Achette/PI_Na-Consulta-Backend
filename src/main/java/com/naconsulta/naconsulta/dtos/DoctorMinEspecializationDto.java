package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Doctor;

import java.io.Serializable;

public class DoctorMinEspecializationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private SpecializationMinDto specialization;

    public DoctorMinEspecializationDto() {
    }

    public DoctorMinEspecializationDto(Long id, String firstName, String lastName, Double evaluation, SpecializationMinDto specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public DoctorMinEspecializationDto(Doctor entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        specialization = new SpecializationMinDto(entity.getSpecialization());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SpecializationMinDto getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationMinDto specialization) {
        this.specialization = specialization;
    }

}
