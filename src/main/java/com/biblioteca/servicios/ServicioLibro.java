package com.biblioteca.servicios;

import com.biblioteca.model.dao.LibroDao;
import com.biblioteca.model.entidades.Libro;

import java.util.List;

public class ServicioLibro {

    private static final LibroDao libroDao = new LibroDao();

    public static List<Libro> listarLibros() {
        return libroDao.listarLibros();
    }

    public static void insertarLibro(Libro libro) {
        libroDao.insertarLibro(libro);
    }
}
