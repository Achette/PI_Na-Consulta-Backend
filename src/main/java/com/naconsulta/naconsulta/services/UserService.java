package com.naconsulta.naconsulta.services;

import com.naconsulta.naconsulta.dtos.*;
import com.naconsulta.naconsulta.entities.Role;
import com.naconsulta.naconsulta.entities.User;
import com.naconsulta.naconsulta.repositories.RoleRepository;
import com.naconsulta.naconsulta.repositories.UserRepository;
import com.naconsulta.naconsulta.services.exceptions.DatabaseException;
import com.naconsulta.naconsulta.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Transactional
    public UserFormDto update(Long id, UserFormDto dto) {
        try {
            User entity = repository.getReferenceById(id);
            authService.validateSelf(id);
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setGender(dto.getGender());
            entity.getRoles().clear();
            for (RoleDto roleDto : dto.getRoles()) {
                Role role = roleRepository.getReferenceById(roleDto.getId());
                entity.getRoles().add(role);
            }

            entity = repository.save(entity);

            return new UserFormDto(entity, entity.getRoles());

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id: " + id + " not found");
        }
    }

    @Transactional
    public UserFormDto insert(UserInsertDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for (RoleDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserFormDto(entity, entity.getRoles());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void delete(Long id) {
        try {
            authService.validateSelf(id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @Transactional(readOnly = true)
    public List<UserMinDto> findAllOrByName(String name) {
        List<User> list = repository.searchByName(name);
        return list.stream().map(x -> new UserMinDto(x)).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public UserMaxDto findById(Long id) {
        authService.validateSelfOrAdmin(id);
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new UserMaxDto(entity, entity.getRoles(), entity.getAppointments());
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @Transactional(readOnly = true)
    public UserMaxDto userLogged() {
        User entity = authService.authenticated();
        return new UserMaxDto(entity, entity.getRoles(), entity.getAppointments());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            logger.error("Usuário não encontrado " + username);
            throw new UsernameNotFoundException("Email não encontrado");
        }
        logger.info("Usuário encontrado " + username);
        return user;
    }
}

