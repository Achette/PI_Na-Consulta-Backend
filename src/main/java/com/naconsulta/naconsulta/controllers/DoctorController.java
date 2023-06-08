package com.naconsulta.naconsulta.controllers;

import com.naconsulta.naconsulta.dtos.DoctorMaxAddressEspecializationDto;
import com.naconsulta.naconsulta.dtos.DoctorMinDto;
import com.naconsulta.naconsulta.dtos.DoctorMinEspecializationDto;
import com.naconsulta.naconsulta.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    @Autowired
    DoctorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorMaxAddressEspecializationDto> findById(@PathVariable Long id) {
        DoctorMaxAddressEspecializationDto dto = service.findbyId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorMinEspecializationDto>> findAllOrByName(
            @RequestParam(name = "name", defaultValue = "") String name) {
        List<DoctorMinEspecializationDto> dto = service.findAllOrByName(name);
        return ResponseEntity.ok(dto);
    }


}
