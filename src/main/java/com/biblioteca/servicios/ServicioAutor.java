package com.biblioteca.servicios;

import com.biblioteca.model.dao.AutorDao;
import com.biblioteca.model.entidades.Autor;
import jakarta.persistence.NoResultException;

import java.util.List;

public class ServicioAutor {

    private static final AutorDao autorDao = new AutorDao();

    public static List<Autor> listarAutores() {
        return autorDao.listarAutores();
    }

    public static Autor buscarAutorPorId(long id) {
        return autorDao.buscarAutorPorId(id);
    }

    public static void insertarAutor(Autor autor) {
        autorDao.insertarAutor(autor);
    }

    public static Autor buscarAutorPorNombre(String nombre) {
        try {
            return autorDao.buscarAutorPorNombre(nombre);
        } catch (NoResultException e) {
            return null;
        }
    }
}
