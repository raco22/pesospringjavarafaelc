package com.ejemplo.pesoideal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "actividades")
    private List<Persona> personas;


}