package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Rol;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CrearAutor", value = "/admin/CrearAutor")
public class CrearAutor extends HttpServlet {

    public CrearAutor() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/crearAutor.jsp";
        request.getRequestDispatcher(vista).forward(request, response);
    }
}