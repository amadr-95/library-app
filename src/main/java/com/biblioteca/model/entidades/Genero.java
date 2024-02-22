package com.biblioteca.model.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @SequenceGenerator(
            name = "genero_sequence",
            sequenceName = "genero_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genero_sequence"
    )
    private Long id;
    @Column(name = "genero", nullable = false)
    private String genero;

    @ManyToMany(mappedBy = "generos")
    private List<Libro> libros;
}
