package com.biblioteca.model.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<Libro> libros = new ArrayList<>();

    public Genero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return genero;
    }
}
