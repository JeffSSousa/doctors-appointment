package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Client;
import com.jeffersonssousa.doctorsappointment.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    public Client save(Client client){
        String secretCrip = encoder.encode(client.getClientSecret());
        client.setClientSecret(secretCrip);
        return repository.save(client);
    }

    public Client findByClientId(String clientId){
        return repository.findByClientId(clientId);
    }

}
