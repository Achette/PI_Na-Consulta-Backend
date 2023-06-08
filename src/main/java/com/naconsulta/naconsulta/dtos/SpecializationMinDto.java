package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Specialization;

import java.io.Serializable;

public class SpecializationMinDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;

    public SpecializationMinDto() {
    }

    public SpecializationMinDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpecializationMinDto(Specialization entity) {
        id = entity.getId();
        name = entity.getName();
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

}
