package com.biblioteca.servicios;

import com.biblioteca.model.dao.GeneroDao;
import com.biblioteca.model.entidades.Genero;

import java.util.List;

public class ServicioGenero {

    private static final GeneroDao generoDao = new GeneroDao();

    public static List<Genero> listarGeneros() {
        return generoDao.listarGeneros();
    }

    public static Genero buscarGeneroPorId(long id) {
        return generoDao.buscarGeneroPorId(id);
    }
}
