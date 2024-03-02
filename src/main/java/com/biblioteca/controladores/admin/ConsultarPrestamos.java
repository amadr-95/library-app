package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioPrestamo;
import com.biblioteca.servicios.ServicioUsuario;
import com.biblioteca.util.EmailSender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ConsultarPrestamos", urlPatterns = {"/admin/ConsultarPrestamos"})
public class ConsultarPrestamos extends HttpServlet {

    public ConsultarPrestamos() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //devolver una lista con toda la información referente a los prestamos
        String vista = "/admin/consultarPrestamos.jsp";
        List<Prestamo> prestamos = ServicioPrestamo.listarPrestamos();
        List<Usuario> sancionados = ServicioUsuario.listarSociosSancionados();
        request.setAttribute("prestamos", prestamos);
        request.setAttribute("sancionados", sancionados);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtenemos la lista de los socios sancionados
        List<Usuario> sancionados = ServicioUsuario.listarSociosSancionados();
        /*List<Usuario> pruebas = List.of(
                new Usuario("Amador", "Sabido", "amasabcar@outlook.com", "password"),
                new Usuario("Ismael", "", "alcala.espigares.ismael@iescamas.es", "password")
        );*/
        EmailSender.enviarCorreos(sancionados, request);
        response.sendRedirect("ConsultarPrestamos");
    }
}
