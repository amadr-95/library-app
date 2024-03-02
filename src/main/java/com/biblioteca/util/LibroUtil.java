package com.biblioteca.util;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.model.entidades.Genero;
import com.biblioteca.servicios.ServicioAutor;
import com.biblioteca.servicios.ServicioGenero;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LibroUtil {
    private static final int TAM_BUFFER = 4 * 1024;
    private static final String isbnRegex = "^\\d{13}$";


    public static boolean validarIsbn(String isbn) {
        return isbn.matches(isbnRegex);
    }

    public static boolean validarEjemplares(int numeroEjemplares) {
        return numeroEjemplares > 0;
    }

    public static void guardarArchivo(Part file, String rutaBase, String nombreArchivo) throws IOException {
        InputStream entrada = file.getInputStream();
        String ruta = rutaBase + File.separator + nombreArchivo;
        FileOutputStream salida = new FileOutputStream(ruta);
        byte[] buffer = new byte[TAM_BUFFER];
        while (entrada.available() > 0) {
            int tam = entrada.read(buffer);
            salida.write(buffer, 0, tam);
        }
        salida.close();
        entrada.close();
    }

    public static void eliminarArchivo(String rutaBase, String nombreArchivo) {
        String ruta = rutaBase + File.separator + nombreArchivo;
        File archivo = new File(ruta);
        if (archivo.exists()) {
            archivo.delete();
        }

    }

    public static boolean camposRequeridosNoVacios(String isbn,
                                                   String titulo,
                                                   String ejemplares,
                                                   String fechaEdicion,
                                                   String[] autores,
                                                   String[] generos) {
        return isbn != null &&
                titulo != null &&
                ejemplares != null &&
                fechaEdicion != null &&
                autores != null &&
                generos != null &&
                !isbn.isEmpty() &&
                !titulo.isEmpty() &&
                !ejemplares.isEmpty() &&
                !fechaEdicion.isEmpty() &&
                autores.length > 0 &&
                generos.length > 0;
    }

    public static List<Autor> obtenerAutores(String[] autoresArray) {
        List<Autor> autores = new ArrayList<>();
        for (String id : autoresArray) {
            Autor autor = ServicioAutor.buscarAutorPorId(Integer.parseInt(id));
            if (autor != null)
                autores.add(autor);
        }
        return autores;
    }

    public static List<Genero> obtenerGeneros(String[] generosArray) {
        List<Genero> generos = new ArrayList<>();
        for (String id : generosArray) {
            Genero genero = ServicioGenero.buscarGeneroPorId(Integer.parseInt(id));
            if (genero != null)
                generos.add(genero);
        }
        return generos;
    }

    public static void listarAutoresYGeneros(HttpServletRequest request) {
        List<Autor> autores = ServicioAutor.listarAutores();
        List<Genero> generos = ServicioGenero.listarGeneros();
        request.setAttribute("autores", autores);
        request.setAttribute("generos", generos);
    }


}
