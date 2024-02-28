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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/gestionAutores.jsp";
        String nombre = request.getParameter("nombre");
        //comprobamos que el nombre no sea nulo o vacio
        if (nombre != null && !nombre.trim().isEmpty()) {
            //comprobamos que no este repetido
            try {
                if (ServicioAutor.buscarAutorPorNombre(nombre) != null)
                    throw new IllegalArgumentException("El nombre del autor ya existe");
                ServicioAutor.insertarAutor(new Autor(nombre));
                response.sendRedirect("GestionAutores");
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                doGet(request, response);
            }
        }
    }
}

