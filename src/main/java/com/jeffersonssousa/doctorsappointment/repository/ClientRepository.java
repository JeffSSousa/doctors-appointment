package com.jeffersonssousa.doctorsappointment.repository;

import com.jeffersonssousa.doctorsappointment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByClientId(String clientId);
}
