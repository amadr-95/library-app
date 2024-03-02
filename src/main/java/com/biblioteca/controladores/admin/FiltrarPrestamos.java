package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.servicios.ServicioPrestamo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FiltrarPrestamos", value = "/admin/FiltrarPrestamos")
public class FiltrarPrestamos extends HttpServlet {

    public FiltrarPrestamos() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/filtrarPrestamos.jsp";
        String filtro = request.getParameter("filtro");
        List<Prestamo> prestamos = ServicioPrestamo.filtrarPrestamos(filtro);
        request.setAttribute("prestamos", prestamos);
        getServletContext().getRequestDispatcher(vista).forward(request, response);

    }
}
