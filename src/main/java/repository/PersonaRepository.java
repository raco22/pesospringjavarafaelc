package com.ejemplo.pesoideal.repository;

import com.ejemplo.pesoideal.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
