package com.jeffersonssousa.doctorsappointment.dto;

public record ClientRequestDTO(String clientId,String clientSecret, String redirectURI, String scope) {

}
