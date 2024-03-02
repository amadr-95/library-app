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

    public static List<Prestamo> listarPrestamosPorUsuario(long id) {
        return prestamoDao.listarPrestamosPorUsuario(id);
    }

    public static List<Prestamo> listarPrestamosPorLibro(long id) {
        return prestamoDao.listarPrestamosPorLibro(id);
    }

    public static Prestamo obtenerPrestamoPorId(long id){
        return prestamoDao.obtenerPrestamoPorId(id);
    }

    public static void insertarPrestamo(Prestamo prestamo){
        prestamoDao.insertarPrestamo(prestamo);
    }

    public static List<Prestamo> listarPrestamos(){
        return prestamoDao.listarPrestamos();
    }

    public static List<Prestamo> filtrarPrestamos(String filtro){
        return prestamoDao.filtrarPrestamos(filtro);
    }
}
