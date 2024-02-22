package com.biblioteca.controladores;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioLibro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Inicio", value = "/Inicio")
public class Inicio extends HttpServlet {

    public Inicio() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/index.jsp";
        //listamos todos los libros
        List<Libro> libros = ServicioLibro.listarLibros();
        if (libros.isEmpty()) {
            // Si la lista está vacía, establecemos un atributo en el request para indicar que no hay libros
            request.setAttribute("mensaje", "No hay libros disponibles en la lista");
        } else {
            // Si la lista no está vacía, establecemos un atributo en el request con la lista de libros
            request.setAttribute("libros", libros);
        }
        getServletContext().getRequestDispatcher(vista).forward(request, response);

    }
}