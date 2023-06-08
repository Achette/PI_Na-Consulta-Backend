package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Address;
import com.naconsulta.naconsulta.entities.Doctor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressMaxDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String publicPlace;
    private String number;
    private String neighborhood;
    private Integer zipCode;
    private String room;
    private CityDto city;

    private List<DoctorNoAddressDto> doctors = new ArrayList<>();

    public AddressMaxDto() {
    }

    public AddressMaxDto(Long id, String publicPlace, String number, String neighborhood, Integer zipCode, String room, CityDto city) {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.room = room;
        this.city = city;
    }

    public AddressMaxDto(Address entity) {
        id = entity.getId();
        publicPlace = entity.getPublicPlace();
        number = entity.getNumber();
        neighborhood = entity.getNeighborhood();
        zipCode = entity.getZipCode();
        room = entity.getRoom();
        city = new CityDto(entity.getCity());
    }

    public AddressMaxDto(Address entity, List<Doctor> doctors) {
        this(entity);
        doctors.forEach(doctor -> this.doctors.add(new DoctorNoAddressDto(doctor)));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public List<DoctorNoAddressDto> getDoctors() {
        return doctors;
    }

}

