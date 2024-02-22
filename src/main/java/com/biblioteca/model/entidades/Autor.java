package com.biblioteca.model.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @SequenceGenerator(
            name = "autor_sequence",
            sequenceName = "autor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "autor_sequence"
    )
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

}
