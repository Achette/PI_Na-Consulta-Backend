package com.naconsulta.naconsulta.controllers;

import com.naconsulta.naconsulta.dtos.AppointmentDto;
import com.naconsulta.naconsulta.dtos.AppointmentMinDto;
import com.naconsulta.naconsulta.dtos.AppointmentUpdateDto;
import com.naconsulta.naconsulta.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping(value = "/report")
    public ResponseEntity<List<AppointmentMinDto>> getReport(
            @RequestParam(name = "minDate", defaultValue = "") String minDateStr,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDateStr,
            @RequestParam(name = "name", defaultValue = "") String nameStr) {
        List<AppointmentMinDto> dto = service.getReport(minDateStr, maxDateStr, nameStr);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentDto> findById(@PathVariable Long id) {
        AppointmentDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AppointmentMinDto> insert(@Valid @RequestBody AppointmentMinDto dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateDto dto) {
        service.updateAppointment(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> appointmentsLoggedUser() {
        List<AppointmentDto> list = service.appointmentsLoggedUser();
        return ResponseEntity.ok().body(list);
    }
}