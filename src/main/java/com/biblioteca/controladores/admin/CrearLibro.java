package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.model.entidades.Genero;
import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioAutor;
import com.biblioteca.servicios.ServicioGenero;
import com.biblioteca.servicios.ServicioLibro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "CrearLibro", urlPatterns = {"/admin/CrearLibro"})
public class CrearLibro extends HttpServlet {

    private final int TAM_BUFFER = 4 * 1024;
    private final String isbnRegex = "^\\d{13}$";


    public CrearLibro() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/crearLibro.jsp";
        //enviar los autores y los generos a la vista
        listarAutoresYGeneros(request);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/crearLibro.jsp";

        //recoger los datos del formulario
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String ejemplaresString = request.getParameter("ejemplares");
        String fechaEdicionString = request.getParameter("fechaEdicion");

        //recoger el archivo de la portada (es opcional)
        Part portada = request.getPart("portada");
        String nombreArchivo = "";
        if (portada != null) {
            nombreArchivo = portada.getSubmittedFileName();
            if(!nombreArchivo.trim().isEmpty())
                guardarArchivo(portada, nombreArchivo);
        }

        //recoger los id de los autores
        String[] autoresArray = request.getParameterValues("autores");
        //recoger los id de los generos
        String[] generosArray = request.getParameterValues("generos");

        //comprobaciones
        if (camposRequeridosNoVacios(isbn, titulo, ejemplaresString, fechaEdicionString, autoresArray, generosArray)) {
            try {
                // Validar el ISBN
                if (!isbn.matches(isbnRegex)) {
                    throw new IllegalArgumentException("El ISBN debe tener 13 caracteres numéricos y sin espacios");
                }

                // Validar que el ISBN no esté en uso
                if (ServicioLibro.buscarLibroPorIsbn(isbn) != null) {
                    throw new IllegalArgumentException("El ISBN ya está en uso");
                }

                int ejemplares = Integer.parseInt(ejemplaresString);

                if(ejemplares < 1) {
                    throw new IllegalArgumentException("El número de ejemplares debe ser mayor que 0");
                }
                LocalDate fechaEdicion = LocalDate.parse(fechaEdicionString);

                // Recoger autores y géneros
                List<Autor> autores = obtenerAutores(autoresArray);
                List<Genero> generos = obtenerGeneros(generosArray);

                // Guardar el libro en la base de datos
                Libro libro = new Libro(isbn, titulo, fechaEdicion, nombreArchivo, ejemplares, autores, generos);
                ServicioLibro.insertarLibro(libro);

                // Redirigir a la página de gestión de libros
                response.sendRedirect("GestionLibros");

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
                request.getRequestDispatcher(vista).forward(request, response);
            }
        } else {
            request.setAttribute("error", "Faltan campos por rellenar");
            listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
            request.getRequestDispatcher(vista).forward(request, response);
        }
    }

    private void guardarArchivo(Part file, String nombreArchivo) throws IOException {
        InputStream entrada = file.getInputStream();
        String ruta = getServletContext().getRealPath("/img")
                + File.separator + nombreArchivo;
        FileOutputStream salida = new FileOutputStream(ruta);
        byte[] buffer = new byte[TAM_BUFFER];
        while (entrada.available() > 0) {
            int tam = entrada.read(buffer);
            salida.write(buffer, 0, tam);
        }
        salida.close();
        entrada.close();
    }

    private void listarAutoresYGeneros(HttpServletRequest request) {
        List<Autor> autores = ServicioAutor.listarAutores();
        List<Genero> generos = ServicioGenero.listarGeneros();
        request.setAttribute("autores", autores);
        request.setAttribute("generos", generos);
    }

    private boolean camposRequeridosNoVacios(String isbn,
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

    private List<Autor> obtenerAutores(String[] autoresArray) {
        List<Autor> autores = new ArrayList<>();
        for (String id : autoresArray) {
            Autor autor = ServicioAutor.buscarAutorPorId(Integer.parseInt(id));
            if(autor != null)
                autores.add(autor);
        }
        return autores;
    }

    private List<Genero> obtenerGeneros(String[] generosArray) {
        List<Genero> generos = new ArrayList<>();
        for (String id : generosArray) {
            Genero genero = ServicioGenero.buscarGeneroPorId(Integer.parseInt(id));
            if(genero != null)
                generos.add(genero);
        }
        return generos;
    }
}