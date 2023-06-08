package com.naconsulta.naconsulta.controllers;

import com.naconsulta.naconsulta.dtos.*;
import com.naconsulta.naconsulta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserFormDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto dto) {
        UserFormDto newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserMinDto>> findAllOrByName(
            @RequestParam(name = "name", defaultValue = "") String name) {
        List<UserMinDto> list = service.findAllOrByName(name);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<UserFormDto> insert(@Valid @RequestBody UserInsertDto dto) {
        UserFormDto newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserMaxDto> userLogged() {
        UserMaxDto dto = service.userLogged();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserMaxDto> findById(@PathVariable Long id) {
        UserMaxDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
