package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.UserMapper;
import com.jeffersonssousa.doctorsappointment.dto.UserRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.User;
import com.jeffersonssousa.doctorsappointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequestDTO dto){
        User user = mapper.toEntity(dto);
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
