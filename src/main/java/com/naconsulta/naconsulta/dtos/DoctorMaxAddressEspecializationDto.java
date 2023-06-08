package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Doctor;

import java.io.Serializable;

public class DoctorMaxAddressEspecializationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Double appointmentPrice;
    private boolean insurance;
    private Double evaluation;
    private SpecializationMinDto specialization;
    private AddressMinDto address;

    public DoctorMaxAddressEspecializationDto() {
    }

    public DoctorMaxAddressEspecializationDto(Long id, String firstName, String lastName, Double appointmentPrice, boolean insurance, Double evaluation, SpecializationMinDto specialization, AddressMinDto address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentPrice = appointmentPrice;
        this.insurance = insurance;
        this.evaluation = evaluation;
        this.specialization = specialization;
        this.address = address;
    }

    public DoctorMaxAddressEspecializationDto(Doctor entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        appointmentPrice = entity.getAppointmentPrice();
        insurance = entity.isInsurance();
        evaluation = entity.getEvaluation();
        specialization = new SpecializationMinDto(entity.getSpecialization());
        address = new AddressMinDto(entity.getAddress());
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

    public Double getAppointmentPrice() {
        return appointmentPrice;
    }

    public void setAppointmentPrice(Double appointmentPrice) {
        this.appointmentPrice = appointmentPrice;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public SpecializationMinDto getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationMinDto specialization) {
        this.specialization = specialization;
    }

    public AddressMinDto getAddress() {
        return address;
    }

    public void setAddress(AddressMinDto address) {
        this.address = address;
    }

}
