package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.model.entidades.Genero;
import com.biblioteca.servicios.ServicioAutor;
import com.biblioteca.servicios.ServicioGenero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GestionGeneros", urlPatterns = {"/admin/GestionGeneros"})
public class GestionGeneros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/gestionGeneros.jsp";
        List<Genero> listaGeneros = ServicioGenero.listarGeneros();
        request.setAttribute("listaGeneros", listaGeneros);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String genero = request.getParameter("genero");
        //comprobamos que el genero no sea nulo o vacio
        if (genero != null && !genero.trim().isEmpty()) {
            //comprobamos que no este repetido
            try {
                if (ServicioGenero.buscarGeneroPorNombre(genero) != null)
                    throw new IllegalArgumentException("El genero ya existe");
                ServicioGenero.insertarGenero(new Genero(genero));
                response.sendRedirect("GestionGeneros");
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                doGet(request, response);
            }
        }
    }
}

