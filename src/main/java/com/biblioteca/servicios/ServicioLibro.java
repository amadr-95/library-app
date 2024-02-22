package com.biblioteca.servicios;

import com.biblioteca.model.dao.LibroDAO;
import com.biblioteca.model.entidades.Libro;

import java.util.List;

public class ServicioLibro {

    public static List<Libro> listarLibros() {
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.listarLibros();
    }
}
