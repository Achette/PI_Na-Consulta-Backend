package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.City;

import java.io.Serializable;

public class CityDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String city;
    private String state;

    public CityDto() {
    }

    public CityDto(Long id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public CityDto(City entity) {
        this.id = entity.getId();
        this.city = entity.getName();
        this.state = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
