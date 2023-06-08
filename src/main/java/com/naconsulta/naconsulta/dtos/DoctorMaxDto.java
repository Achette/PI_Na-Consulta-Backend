package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Appointment;
import com.naconsulta.naconsulta.entities.Doctor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorMaxDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Double appointmentPrice;
    private boolean insurance;
    private Double evaluation;
    private SpecializationDto specialization;
    private AddressMaxDto address;
    private List<AppointmentDto> appointments = new ArrayList<>();

    public DoctorMaxDto() {
    }

    public DoctorMaxDto(Long id, String firstName, String lastName, Double appointmentPrice, boolean insurance, Double evaluation, SpecializationDto specialization, AddressMaxDto address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentPrice = appointmentPrice;
        this.insurance = insurance;
        this.evaluation = evaluation;
        this.specialization = specialization;
        this.address = address;
    }

    public DoctorMaxDto(Doctor entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        appointmentPrice = entity.getAppointmentPrice();
        insurance = entity.isInsurance();
        evaluation = entity.getEvaluation();
        specialization = new SpecializationDto(entity.getSpecialization());
        address = new AddressMaxDto(entity.getAddress());
    }

    public DoctorMaxDto(Doctor entity, List<Appointment> appointments) {
        this(entity);
        appointments.forEach(app -> this.appointments.add(new AppointmentDto(app)));
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

    public SpecializationDto getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SpecializationDto specialization) {
        this.specialization = specialization;
    }

    public AddressMaxDto getAddress() {
        return address;
    }

    public void setAddress(AddressMaxDto address) {
        this.address = address;
    }

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }

}
