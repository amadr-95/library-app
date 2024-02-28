package com.biblioteca.servicios;

import com.biblioteca.model.dao.PrestamoDao;
import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Usuario;

import java.time.LocalDate;
import java.util.List;

public class ServicioPrestamo {

    private static final PrestamoDao prestamoDao = new PrestamoDao();

    /*public static List<Prestamo> listarPrestamosPorUsuario(Usuario usuario){
        return prestamoDao.listarPrestamosPorUsuario(usuario);
    }*/

    public static void establecerFechaDevolucion(long prestamoId, LocalDate fechaDevolucion){
        prestamoDao.establecerFechaDevolucion(prestamoId, fechaDevolucion);
    }
}
