package com.ejemplo.pesoideal.controller;

import com.ejemplo.pesoideal.model.Persona;
import com.ejemplo.pesoideal.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaRepository.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> mostrarPersona(@PathVariable Long id) {
        return personaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Persona> mostrarPersonas() {
        return personaRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        return personaRepository.findById(id)
                .map(existingPersona -> {
                    existingPersona.setNombre(persona.getNombre());
                    existingPersona.setEdad(persona.getEdad());
                    return ResponseEntity.ok(personaRepository.save(existingPersona));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generarReporte() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        byte[] bytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_peso_ideal.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}