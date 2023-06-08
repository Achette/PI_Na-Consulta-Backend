package com.naconsulta.naconsulta.services;

import com.naconsulta.naconsulta.dtos.AppointmentDto;
import com.naconsulta.naconsulta.dtos.AppointmentMinDto;
import com.naconsulta.naconsulta.dtos.AppointmentUpdateDto;
import com.naconsulta.naconsulta.entities.Appointment;
import com.naconsulta.naconsulta.entities.Doctor;
import com.naconsulta.naconsulta.entities.User;
import com.naconsulta.naconsulta.repositories.AppointmentRepository;
import com.naconsulta.naconsulta.repositories.DoctorRepository;
import com.naconsulta.naconsulta.repositories.UserRepository;
import com.naconsulta.naconsulta.services.exceptions.DatabaseException;
import com.naconsulta.naconsulta.services.exceptions.ResourceNotFoundException;
import com.naconsulta.naconsulta.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AuthService authService;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<AppointmentMinDto> getReport(String minDateStr, String maxDateStr, String name) {

        LocalDate minDate = minDateStr.equals("") ? LocalDate.now() : LocalDate.parse(minDateStr);
        LocalDate maxDate = maxDateStr.equals("") ? LocalDate.now().plusMonths(1) : LocalDate.parse(maxDateStr);

        LocalDateTime minDateTime = LocalDateTime.of(minDate, LocalTime.MIN);
        LocalDateTime maxDateTime = LocalDateTime.of(maxDate, LocalTime.MAX);

        Instant minInstant = minDateTime.toInstant(ZoneOffset.UTC);
        Instant maxInstant = maxDateTime.toInstant(ZoneOffset.UTC);

        List<Appointment> list = appointmentRepository.searchAppointments(minInstant, maxInstant, name);

        return list.stream().map(appointment -> new AppointmentMinDto(appointment)).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Transactional
    public void delete(Long id) {
        try {
            authService.validateDeleteAccess(id);
            appointmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public AppointmentDto findById(Long id) {
        try {
            authService.validateAppointmentAccess(id);

            Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            return new AppointmentDto(appointment);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Transactional
    public AppointmentMinDto insert(AppointmentMinDto dto) {
        authService.validateSelf(dto.getUserId());

        if (dto.getUserId().equals(dto.getDoctorId())) {
            throw new UnauthorizedException("O userId e o doctorId não podem ser os mesmos.");
        }

        Appointment entity = new Appointment();
        entity.setDate(dto.getDate());
        User user = userRepository.getReferenceById(dto.getUserId());
        Doctor doctor = doctorRepository.getReferenceById(dto.getDoctorId());
        entity.setUser(user);
        entity.setDoctor(doctor);
        entity = appointmentRepository.save(entity);
        return new AppointmentMinDto(entity);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR')")
    @Transactional
    public void updateAppointment(Long id, AppointmentUpdateDto dto) {
        try {
            Appointment appointment = appointmentRepository.getReferenceById(id);
            authService.validateAppointmentDoctor(id);
            if (appointment.checkUpdateDate(appointment.getDate())) {
                throw new UnauthorizedException("A data é posterior à data de atualização.");
            }
            appointment.setDiagnosis(dto.getDiagnosis());
            appointment.setSymptom(dto.getSymptom());
            appointmentRepository.save(appointment);
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("Data de agendamento é posterior à data de atualização");
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public List<AppointmentDto> appointmentsLoggedUser() {
        User user = authService.authenticated();
        List<Appointment> list = appointmentRepository.findByUser(user);
        return list.stream().map(x -> new AppointmentDto(x)).collect(Collectors.toList());
    }

}