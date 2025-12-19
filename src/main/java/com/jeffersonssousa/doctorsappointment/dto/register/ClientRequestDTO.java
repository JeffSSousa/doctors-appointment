package com.jeffersonssousa.doctorsappointment.dto.register;

public record ClientRequestDTO(String clientId,String clientSecret, String redirectURI, String scope) {

}
