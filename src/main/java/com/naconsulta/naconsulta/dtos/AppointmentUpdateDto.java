package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Appointment;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.Instant;

public class AppointmentUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant updatedAt;

    private Instant date;

    @NotBlank(message = "Campo obrigatório")
    private String diagnosis;

    @NotBlank(message = "Campo obrigatório")
    private String symptom;


    public AppointmentUpdateDto() {
    }

    public AppointmentUpdateDto(Instant updatedAt, Instant date, String diagnosis, String symptom) {
        this.updatedAt = updatedAt;
        this.date = date;
        this.diagnosis = diagnosis;
        this.symptom = symptom;
    }

    public AppointmentUpdateDto(Appointment entity) {
        updatedAt = entity.getUpdatedAt();
        date = entity.getDate();
        diagnosis = entity.getDiagnosis();
        symptom = entity.getSymptom();
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDate() {
        return date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getSymptom() {
        return symptom;
    }

}
