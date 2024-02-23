package com.biblioteca.model.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "libros",
        uniqueConstraints = {
                @UniqueConstraint(name = "isbn_unique", columnNames = "isbn")
        }
)
public class Libro {
    @Id
    @SequenceGenerator(
            name = "libro_sequence",
            sequenceName = "libro_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "libro_sequence"
    )
    private Long id;

    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "fecha_edicion", nullable = false)
    private LocalDate fechaEdicion;

    @Column(name = "imagen_portada")
    private String imagenPortada;

    @Column(name = "numero_ejemplares", nullable = false)
    private int numeroEjemplares;

    //un libro puede tener varios autores
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor", //nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id", nullable = false), //fk de la tabla actual
            inverseJoinColumns = @JoinColumn(name = "autor_id", nullable = false) //fk de la otra tabla (autor)
    )
    private List<Autor> autores = new ArrayList<>();

    //un libro puede pertenecer a varios generos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_genero", //nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id", nullable = false), //fk de la tabla actual
            inverseJoinColumns = @JoinColumn(name = "genero_id", nullable = false) //fk de la otra tabla (genero)
    )
    private List<Genero> generos = new ArrayList<>();

    //un libro puede tener varios prestamos
    @OneToMany(mappedBy = "libro")
    private List<Prestamo> prestamos = new ArrayList<>();

    public Libro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDate fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public int getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public void setNumeroEjemplares(int numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
