package com.biblioteca.servicios;

import com.biblioteca.model.dao.GeneroDao;
import com.biblioteca.model.entidades.Genero;
import jakarta.persistence.NoResultException;

import java.util.List;

public class ServicioGenero {

    private static final GeneroDao generoDao = new GeneroDao();

    public static List<Genero> listarGeneros() {
        return generoDao.listarGeneros();
    }

    public static Genero buscarGeneroPorId(long id) {
        return generoDao.buscarGeneroPorId(id);
    }

    public static Genero buscarGeneroPorNombre(String nombre) {
        try {
            return generoDao.buscarGeneroPorNombre(nombre);
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void insertarGenero(Genero genero) {
        generoDao.insertarGenero(genero);
    }

    public static void eliminarGenero(Genero genero) {
        generoDao.eliminarGenero(genero);
    }
}
