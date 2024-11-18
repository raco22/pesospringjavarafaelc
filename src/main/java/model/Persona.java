package com.ejemplo.pesoideal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int edad;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<HistorialPeso> historialPesos;

    @ManyToMany
    @JoinTable(
            name = "persona_actividad",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "actividad_id")
    )
    private List<Actividad> actividades;


}
