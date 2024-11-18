package com.ejemplo.pesoideal.model;

import javax.persistence.*;

@Entity
public class HistorialPeso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double peso;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;


}