package com.naconsulta.naconsulta.services;

import com.naconsulta.naconsulta.dtos.DoctorMaxAddressEspecializationDto;
import com.naconsulta.naconsulta.dtos.DoctorMinDto;
import com.naconsulta.naconsulta.dtos.DoctorMinEspecializationDto;
import com.naconsulta.naconsulta.entities.Doctor;
import com.naconsulta.naconsulta.repositories.DoctorRepository;
import com.naconsulta.naconsulta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository repository;

    @Transactional(readOnly = true)
    public DoctorMaxAddressEspecializationDto findbyId(Long id) {
        Doctor entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new DoctorMaxAddressEspecializationDto(entity);
    }

    @Transactional(readOnly = true)
    public List<DoctorMinEspecializationDto> findAllOrByName(String name) {
        List<Doctor> result = repository.searchByName(name);

        return result.stream().map(x -> new DoctorMinEspecializationDto(x)).collect(Collectors.toList());
    }


}
