package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.servicios.ServicioPrestamo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GestionLibros", urlPatterns = {"/admin/GestionLibros"})
public class GestionLibros extends HttpServlet {

    public GestionLibros() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/gestionLibros.jsp";
        List<Libro> listaLibros = ServicioLibro.listarLibros();
        request.setAttribute("listaLibros", listaLibros);
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
