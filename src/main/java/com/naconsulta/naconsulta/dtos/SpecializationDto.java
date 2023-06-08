package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Doctor;
import com.naconsulta.naconsulta.entities.Specialization;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SpecializationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private Set<DoctorMinDto> doctors = new HashSet<>();

    public SpecializationDto() {
    }

    public SpecializationDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpecializationDto(Specialization entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public SpecializationDto(Specialization entity, Set<Doctor> doctors) {
        this(entity);
        doctors.forEach(doctor -> this.doctors.add(new DoctorMinDto(doctor)));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DoctorMinDto> getDoctors() {
        return doctors;
    }
}
