package com.biblioteca.controladores.socio;

import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioPrestamo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GestionPrestamos", urlPatterns = {"/socio/GestionPrestamos"})
public class GestionPrestamos extends HttpServlet{

    public GestionPrestamos() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/socio/gestionPrestamos.jsp";
        Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
        if(usuario != null){
            request.setAttribute("prestamos", usuario.getPrestamos());
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
