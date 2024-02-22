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
    @ManyToMany()
    @JoinTable(
            name = "libro_autor", //nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id", nullable = false), //fk de la tabla actual
            inverseJoinColumns = @JoinColumn(name = "autor_id", nullable = false) //fk de la otra tabla (autor)
    )
    private List<Autor> autores = new ArrayList<>();

    //un libro puede pertenecer a varios generos
    @ManyToMany()
    @JoinTable(
            name = "libro_genero", //nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id", nullable = false), //fk de la tabla actual
            inverseJoinColumns = @JoinColumn(name = "genero_id", nullable = false) //fk de la otra tabla (genero)
    )
    private List<Genero> generos = new ArrayList<>();

    //un libro solo puede tener un prestamo
    @OneToOne(mappedBy = "libro")
    private Prestamo prestamo;

    //
    public Libro() {
    }


}
