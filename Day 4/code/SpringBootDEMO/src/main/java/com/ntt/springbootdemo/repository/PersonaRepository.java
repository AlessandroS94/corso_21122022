package com.ntt.springbootdemo.repository;

import com.ntt.springbootdemo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}