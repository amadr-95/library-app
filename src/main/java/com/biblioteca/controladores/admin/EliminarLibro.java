package com.biblioteca.controladores.admin;

import com.biblioteca.servicios.ServicioLibro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminarLibro", urlPatterns = {"/admin/EliminarLibro"})
public class EliminarLibro extends HttpServlet {

    public EliminarLibro() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/gestionLibros.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long libroId = Long.parseLong(id);
            //Eliminamos el libro de la base de datos, pero antes tenemos que eliminar los registros de la tabla
            //libro_autor y libro_genero que tengan el id del libro
            ServicioLibro.eliminarLibro(libroId);
            response.sendRedirect("GestionLibros");
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
