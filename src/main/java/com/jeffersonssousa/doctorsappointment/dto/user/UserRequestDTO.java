package com.jeffersonssousa.doctorsappointment.dto.user;

import java.util.List;

public record UserRequestDTO(String login, String password, List<String> roles) {
}
