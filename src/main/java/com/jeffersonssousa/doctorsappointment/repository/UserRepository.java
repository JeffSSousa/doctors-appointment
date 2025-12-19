package com.jeffersonssousa.doctorsappointment.repository;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Login, UUID> {

    Optional<Login> findByLogin(String login);

    Optional<Login> findByEmail(String email);
}
