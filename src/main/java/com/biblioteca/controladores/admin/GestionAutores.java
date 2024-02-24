package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.servicios.ServicioAutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GestionAutores", urlPatterns = {"/admin/GestionAutores"})
public class GestionAutores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/gestionAutores.jsp";
        List<Autor> listaAutores = ServicioAutor.listarAutores();
        request.setAttribute("listaAutores", listaAutores);
        request.getRequestDispatcher(vista).forward(request, response);
    }
}

