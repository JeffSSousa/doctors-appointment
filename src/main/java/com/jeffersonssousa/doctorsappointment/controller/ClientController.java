package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.ClientMapper;
import com.jeffersonssousa.doctorsappointment.dto.ClientRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Client;
import com.jeffersonssousa.doctorsappointment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> save(@RequestBody ClientRequestDTO dto){
        Client client = mapper.toEntity(dto);
        service.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findByClientId(@PathVariable String clientId){
        var client = service.findByClientId(clientId);
        return ResponseEntity.ok().body(client);
    }
}
