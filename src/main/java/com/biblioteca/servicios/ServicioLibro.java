package com.biblioteca.servicios;

import com.biblioteca.model.dao.LibroDao;
import com.biblioteca.model.entidades.Libro;

import java.util.List;

public class ServicioLibro {

    private static final LibroDao libroDao = new LibroDao();

    public static List<Libro> listarLibros() {
        return libroDao.listarLibros();
    }

    public static void insertarLibro(Libro libro) throws IllegalArgumentException {
        libroDao.insertarLibro(libro);
    }

    public static Libro buscarLibroPorIsbn(String isbn) {
        try{
            return libroDao.buscarLibroPorIsbn(isbn);
        } catch (Exception e) {
            return null;
        }
    }

    public static void eliminarLibro(long id) {
        libroDao.eliminarLibro(id);
    }
}
