package com.biblioteca.controladores.empleado;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RealizarPrestamo", urlPatterns = {"/empleado/RealizarPrestamo"})
public class RealizarPrestamo extends HttpServlet {

    public RealizarPrestamo() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/empleado/realizarPrestamo.jsp";
        //mostar una lista con los libros disponibles para prestar (unidades > 0)
        List<Libro> libros = ServicioLibro.listarLibrosDisponiblesParaPrestar();
        request.setAttribute("libros", libros);
        //mostrar una lista con los socios NO sancionados
        List<Usuario> socios = ServicioUsuario.listarSociosNoSancionados();
        request.setAttribute("socios", socios);
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
