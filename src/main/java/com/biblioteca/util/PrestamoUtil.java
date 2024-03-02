package com.biblioteca.util;

import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.servicios.ServicioPrestamo;

import java.time.LocalDate;
import java.time.Period;

public class PrestamoUtil {

    private static final int DIAS_MAXIMO_PRESTAMO = 15;

    public static int getDiasPrestamo(long prestamoId) {
        Prestamo prestamo = ServicioPrestamo.obtenerPrestamoPorId(prestamoId);
        LocalDate fechaPrestamo = prestamo.getFechaPrestamo();
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        return fechaDevolucion == null ?
                Period.between(fechaPrestamo, LocalDate.now()).getDays() : //dinamico
                Period.between(fechaPrestamo, fechaDevolucion).getDays(); //fijo
    }

    public static boolean prestamoVigente(long prestamoId) {
        return getDiasPrestamo(prestamoId) <= DIAS_MAXIMO_PRESTAMO;
    }


}
