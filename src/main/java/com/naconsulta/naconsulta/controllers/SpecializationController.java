package com.naconsulta.naconsulta.controllers;

import com.naconsulta.naconsulta.dtos.SpecializationDto;
import com.naconsulta.naconsulta.dtos.SpecializationMinDto;
import com.naconsulta.naconsulta.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/specializations")
public class SpecializationController {

    @Autowired
    SpecializationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpecializationDto> findById(@PathVariable Long id) {
        SpecializationDto dto = service.findbyId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SpecializationMinDto>> findAllOrByName(
            @RequestParam(name = "name", defaultValue = "") String name) {
        List<SpecializationMinDto> dto = service.findAllOrByName(name);
        return ResponseEntity.ok(dto);
    }


}
