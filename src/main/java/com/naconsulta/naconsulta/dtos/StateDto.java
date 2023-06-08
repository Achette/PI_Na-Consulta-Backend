package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.City;
import com.naconsulta.naconsulta.entities.State;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class StateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    Set<CityDto> cities = new HashSet<>();

    public StateDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateDto(State entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public StateDto(State entity, Set<City> cities) {
        this(entity);
        cities.forEach(city -> this.cities.add(new CityDto(city)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CityDto> getCities() {
        return cities;
    }

}
