package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.model.entidades.Genero;
import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.util.LibroUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@MultipartConfig
@WebServlet(name = "CrearLibro", urlPatterns = {"/admin/CrearLibro"})
public class CrearLibro extends HttpServlet {

    public CrearLibro() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/crearLibro.jsp";
        //enviar los autores y los generos a la vista
        LibroUtils.listarAutoresYGeneros(request);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String vista = "/admin/crearLibro.jsp";

        //recoger los datos del formulario
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String ejemplaresString = request.getParameter("ejemplares");
        String fechaEdicionString = request.getParameter("fechaEdicion");

        //recoger el archivo de la portada (es opcional)
        Part portada = request.getPart("portada");
        String nombreArchivo = portada.getSubmittedFileName();

        //recoger los id de los autores
        String[] autoresArray = request.getParameterValues("autores");
        //recoger los id de los generos
        String[] generosArray = request.getParameterValues("generos");

        //comprobaciones
        if (LibroUtils.camposRequeridosNoVacios(isbn, titulo, ejemplaresString, fechaEdicionString, autoresArray, generosArray)) {
            try {
                // Validar el ISBN
                if (!LibroUtils.validarIsbn(isbn)) {
                    throw new IllegalArgumentException("El ISBN debe tener 13 caracteres numéricos y sin espacios");
                }

                // Validar que el ISBN no esté en uso
                if (ServicioLibro.buscarLibroPorIsbn(isbn) != null) {
                    throw new IllegalArgumentException("El ISBN ya está en uso");
                }

                LocalDate fechaEdicion = LocalDate.parse(fechaEdicionString);

                int ejemplares = Integer.parseInt(ejemplaresString);

                if (!LibroUtils.validarEjemplares(ejemplares)) {
                    throw new IllegalArgumentException("El número de ejemplares debe ser mayor que 0");
                }

                // Recoger autores y géneros
                List<Autor> autores = LibroUtils.obtenerAutores(autoresArray);
                List<Genero> generos = LibroUtils.obtenerGeneros(generosArray);

                //guardar el archivo de la portada
                if (!nombreArchivo.isEmpty()) {
                    String ruta = getServletContext().getRealPath("/img");
                    LibroUtils.guardarArchivo(portada, ruta, nombreArchivo);
                }

                // Guardar el libro en la base de datos
                Libro libro = new Libro(isbn, titulo, fechaEdicion, nombreArchivo, ejemplares, autores, generos);
                ServicioLibro.insertarLibro(libro);

                // Redirigir a la página de gestión de libros
                response.sendRedirect("GestionLibros");
                return;

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                LibroUtils.listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
            }
        } else {
            request.setAttribute("error", "Faltan campos por rellenar");
            LibroUtils.listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}