package com.naconsulta.naconsulta.dtos;

import com.naconsulta.naconsulta.entities.Appointment;
import com.naconsulta.naconsulta.entities.Role;
import com.naconsulta.naconsulta.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMaxDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String firstName;

    @NotBlank(message = "Campo obrigatório")
    private String lastName;

    @NotBlank(message = "Campo obrigatório")
    private String gender;

    @Email(message = "Favor inserir um email válido")
    private String email;

    private List<AppointmentDto> appointments = new ArrayList<>();

    private Set<RoleDto> roles = new HashSet<>();

    public UserMaxDto() {
    }

    public UserMaxDto(Long id, String firstName, String lastName, String gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public UserMaxDto(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        gender = entity.getGender();
        email = entity.getEmail();
    }

    public UserMaxDto(User entity, Set<Role> roles, List<Appointment> appointments) {
        this(entity);
        roles.forEach(role -> this.roles.add(new RoleDto(role)));
        appointments.forEach((appointment -> this.appointments.add(new AppointmentDto(appointment))));
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }
}
