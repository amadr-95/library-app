package com.biblioteca.servicios;

import com.biblioteca.model.dao.LibroDao;
import com.biblioteca.model.entidades.Libro;

import java.util.List;

public class ServicioLibro {

    public static List<Libro> listarLibros() {
        LibroDao libroDAO = new LibroDao();
        return libroDAO.listarLibros();
    }
}
