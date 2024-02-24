package com.biblioteca.servicios;

import com.biblioteca.model.dao.AutorDao;
import com.biblioteca.model.entidades.Autor;

import java.util.List;

public class ServicioAutor {

    private static final AutorDao autorDao = new AutorDao();

    public static List<Autor> listarAutores() {
        return autorDao.listarAutores();
    }

    public static Autor buscarAutorPorId(long id) {
        return autorDao.buscarAutorPorId(id);
    }
}
