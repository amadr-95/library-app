package com.biblioteca.model.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @SequenceGenerator(
            name = "prestamo_sequence",
            sequenceName = "prestamo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "prestamo_sequence"
    )
    private Long id;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @OneToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
