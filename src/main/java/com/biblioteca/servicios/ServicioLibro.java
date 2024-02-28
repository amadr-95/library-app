package com.biblioteca.servicios;

import com.biblioteca.model.dao.LibroDao;
import com.biblioteca.model.entidades.Libro;

import java.util.List;

public class ServicioLibro {

    private static final LibroDao libroDao = new LibroDao();

    public static List<Libro> listarLibros() {
        return libroDao.listarLibros();
    }

    public static void insertarLibro(Libro libro) throws IllegalArgumentException{
        //compobaciones antes de insertar el libro
        if(libro.getIsbn().trim().isEmpty() || libro.getIsbn().length() != 13)
            throw new IllegalArgumentException("isbn no válido");
        libroDao.insertarLibro(libro);
    }
}
