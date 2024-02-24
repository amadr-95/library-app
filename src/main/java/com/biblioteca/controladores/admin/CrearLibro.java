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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        listarAutoresYGeneros(request);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //TODO
        /*
        String vista = "/admin/crearLibro.jsp";
        //enviar los autores y los generos a la vista
        listarAutoresYGeneros(request);
        //recoger los datos del formulario
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        int ejemplares = Integer.parseInt(request.getParameter("ejemplares"));
        LocalDate fechaEdicion = LocalDate.parse(request.getParameter("fechaEdicion"));

        String nombreArchivo = "default.jpg";
        //recoger el archivo de imagen de la portada
        Part parte = request.getPart("portada");
        if (parte != null) {

            //esto es lo que se guardara en la bbdd
            nombreArchivo = parte.getSubmittedFileName();
            String ruta = request.getServletContext().getRealPath("/img/" + nombreArchivo);
            try (OutputStream salida = new FileOutputStream(ruta)) {
                byte[] buffer = new byte[1024];
                int leido;
                while ((leido = parte.getInputStream().read(buffer)) != -1) {
                    salida.write(buffer, 0, leido);
                }
            } catch (IOException e) {
                response.sendRedirect("GestionLibros");
                return;
            }
        }

        //recoger los id de los autores
        String[] autoresArray = request.getParameterValues("autores");
        List<Autor> autores = new ArrayList<>();
        for (String id : autoresArray) {
            Autor autor = ServicioAutor.buscarAutorPorId(Long.parseLong(id));
            if (autor != null)
                autores.add(autor);
        }

        //recoger los id de los generos
        String[] generosArray = request.getParameterValues("generos");
        List<Genero> generos = new ArrayList<>();
        for (String id : generosArray) {
            Genero genero = ServicioGenero.buscarGeneroPorId(Long.parseLong(id));
            if (genero != null)
                generos.add(genero);
        }


        *//*try {
            ServicioLibro.comprobarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }*//*

        //vamos a suponer que todos los campos son correctos
        //guardar el libro en la base de datos
        Libro libro = new Libro(
                isbn, titulo, fechaEdicion, nombreArchivo, ejemplares, autores, generos);
        ServicioLibro.insertarLibro(libro);
        //redirigir a la pagina de gestion de libros
        response.sendRedirect("GestionLibros");

        *//*
        if (
                nombre != null && apellidos != null && email != null && password != null &&
                        !nombre.trim().isEmpty() && !email.trim().isEmpty() && !password.trim().isEmpty()
        ) {
            //comprobar que el email no esta en uso
            Usuario usuario = ServicioUsuario.buscarUsuarioPorEmail(email);
            if (usuario != null && !usuario.getEmail().equals(email)) {
                request.setAttribute("error", "El email ya esta en uso");
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                //guardar el usuario en la base de datos
                Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, password, rol);
                ServicioUsuario.insertarUsuario(nuevoUsuario);
                //redirigir a la pagina de gestion de empleados
                response.sendRedirect("GestionEmpleados");
            }
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    */
    }

    private void listarAutoresYGeneros(HttpServletRequest request) {
        List<Autor> autores = ServicioAutor.listarAutores();
        List<Genero> generos = ServicioGenero.listarGeneros();
        request.setAttribute("autores", autores);
        request.setAttribute("generos", generos);
    }
}