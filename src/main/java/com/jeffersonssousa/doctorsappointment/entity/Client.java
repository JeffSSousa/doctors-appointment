package com.jeffersonssousa.doctorsappointment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String clientId;

    private String clientSecret;

    private String redirectURI;

    private String scope;

}
