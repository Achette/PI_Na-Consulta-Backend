package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Address;

import java.io.Serializable;

public class AddressMinDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String publicPlace;
    private String number;
    private String neighborhood;
    private Integer zipCode;
    private String room;
    private CityDto city;

    public AddressMinDto() {
    }

    public AddressMinDto(Long id, String publicPlace, String number, String neighborhood, Integer zipCode, String room, CityDto city) {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.room = room;
        this.city = city;
    }

    public AddressMinDto(Address entity) {
        id = entity.getId();
        publicPlace = entity.getPublicPlace();
        number = entity.getNumber();
        neighborhood = entity.getNeighborhood();
        zipCode = entity.getZipCode();
        room = entity.getRoom();
        city = new CityDto(entity.getCity());
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
}

