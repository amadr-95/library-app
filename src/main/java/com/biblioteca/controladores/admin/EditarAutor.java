package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.servicios.ServicioAutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditarAutor", urlPatterns = {"/admin/EditarAutor"})
public class EditarAutor extends HttpServlet {

    public EditarAutor() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/gestionAutores.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long autorId = Long.parseLong(id);
            Autor autor = ServicioAutor.buscarAutorPorId(autorId);
            if (autor != null) {
                request.setAttribute("autorEditar", autor);
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                response.sendRedirect("GestionAutores");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}


