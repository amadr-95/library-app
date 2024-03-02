package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.model.entidades.Genero;
import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.util.LibroUtil;
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
@WebServlet(name = "EditarLibro", urlPatterns = {"/admin/EditarLibro"})
public class EditarLibro extends HttpServlet {

    public EditarLibro() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //cuando accedemos por GET debemos mostrar el formulario con los datos del usuario
        String vista = "/admin/editarLibro.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long libroId = Long.parseLong(id);
            //buscamos el libro por su id
            Libro libro = ServicioLibro.buscarLibroPorId(libroId);
            if (libro != null) {
                request.setAttribute("libroEditar", libro);
                LibroUtil.listarAutoresYGeneros(request);
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                response.sendRedirect("GestionLibros");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String vista = "/admin/editarLibro.jsp";

        //recoger los datos del formulario
        long id = Long.parseLong(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String ejemplaresString = request.getParameter("ejemplares");
        String fechaEdicionString = request.getParameter("fechaEdicion");

        //recoger el archivo de la portada (es opcional)
        Part portada = request.getPart("portada");

        //recoger los id de los autores
        String[] autoresArray = request.getParameterValues("autores");
        //recoger los id de los generos
        String[] generosArray = request.getParameterValues("generos");

        //comprobaciones
        if (LibroUtil.camposRequeridosNoVacios(isbn, titulo, ejemplaresString, fechaEdicionString, autoresArray, generosArray)) {
            //obtenemos el libro por su id
            Libro libroActual = ServicioLibro.buscarLibroPorId(id);
            if (libroActual != null) {
                try {
                    // Validar el ISBN
                    if (!LibroUtil.validarIsbn(isbn)) {
                        throw new IllegalArgumentException("El ISBN debe tener 13 caracteres numéricos y sin espacios");
                    }

                    // Validar que el ISBN no esté en uso por otro libro
                    Libro libro = ServicioLibro.buscarLibroPorIsbn(isbn);
                    if (libro != null && libro.getId() != id) {
                        throw new IllegalArgumentException("El ISBN ya está en uso");
                    }

                    int ejemplares = Integer.parseInt(ejemplaresString);

                    if (!LibroUtil.validarEjemplares(ejemplares)) {
                        throw new IllegalArgumentException("El número de ejemplares debe ser mayor que 0");
                    }

                    LocalDate fechaEdicion = LocalDate.parse(fechaEdicionString);

                    // Recoger autores y géneros
                    List<Autor> autores = LibroUtil.obtenerAutores(autoresArray);
                    List<Genero> generos = LibroUtil.obtenerGeneros(generosArray);

                    String nombreArchivo = portada.getSubmittedFileName();
                    if (!nombreArchivo.trim().isEmpty()) {
                        //si el nombre del archivo no esta vacio, guardamos el archivo nuevo
                        String ruta = getServletContext().getRealPath("/img");
                        LibroUtil.guardarArchivo(portada, ruta, nombreArchivo);
                        //eliminamos el archivo que hubiese en ese momento
                        LibroUtil.eliminarArchivo(ruta, request.getParameter("portadaActual"));
                    } else {
                        //si el nombre del archivo esta vacio, usamos el nombre del archivo actual
                        nombreArchivo = request.getParameter("portadaActual");
                    }
                    // Guardar el libro en la base de datos
                    //Libro libroEditado = new Libro(id, isbn, titulo, fechaEdicion, nombreArchivo, ejemplares, autores, generos);
                    //seteamos los nuevos valores al libro
                    libroActual.setIsbn(isbn);
                    libroActual.setTitulo(titulo);
                    libroActual.setFechaEdicion(fechaEdicion);
                    libroActual.setImagenPortada(nombreArchivo);
                    libroActual.setNumeroEjemplares(ejemplares);
                    libroActual.setAutores(autores);
                    libroActual.setGeneros(generos);
                    //actualizamos el libroActual
                    ServicioLibro.actualizarLibro(libroActual);

                    // Redirigir a la página de gestión de libros
                    response.sendRedirect("GestionLibros");
                    return;

                } catch (IllegalArgumentException e) {
                    request.setAttribute("error", e.getMessage());
                    LibroUtil.listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
                    request.setAttribute("libroEditar",
                            new Libro(
                                    id,
                                    isbn,
                                    titulo,
                                    LocalDate.parse(fechaEdicionString),
                                    request.getParameter("portadaActual"),
                                    Integer.parseInt(ejemplaresString),
                                    LibroUtil.obtenerAutores(autoresArray),
                                    LibroUtil.obtenerGeneros(generosArray))
                    );
                }
            }
        } else {
            request.setAttribute("error", "Faltan campos por rellenar");
            LibroUtil.listarAutoresYGeneros(request); // Enviar autores y géneros a la vista
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }

}