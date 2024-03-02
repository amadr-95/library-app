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
import java.util.List;

@WebServlet(name = "GestionPrestamos", urlPatterns = {"/socio/GestionPrestamos"})
public class GestionPrestamos extends HttpServlet {

    public GestionPrestamos() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/socio/gestionPrestamos.jsp";
        Usuario usuarioSesion = (Usuario) request.getSession(false).getAttribute("usuario");
        if (usuarioSesion != null) {
            long id = usuarioSesion.getId();
            List<Prestamo> prestamos = ServicioPrestamo.listarPrestamosPorUsuario(id);
            request.setAttribute("prestamos", prestamos);
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
