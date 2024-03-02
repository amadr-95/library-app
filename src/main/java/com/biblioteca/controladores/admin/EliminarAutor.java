package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.servicios.ServicioAutor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EliminarAutor", urlPatterns = {"/admin/EliminarAutor"})
public class EliminarAutor extends HttpServlet {

    public EliminarAutor() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/gestionAutores.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long autorId = Long.parseLong(id);
            //comprobamos que el autor no tenga libros asociados
            Autor autor = ServicioAutor.buscarAutorPorId(autorId);
            if (autor != null) {
                if (autor.getLibros().isEmpty()){
                    //Eliminamos el autor de la base de datos
                    ServicioAutor.eliminarAutor(autor);
                    //response.sendRedirect("GestionAutores");
                    //return;
                } else{
                    request.setAttribute("error", "No se puede eliminar el autor porque tiene libros asociados");
                }
            }
        }
        response.sendRedirect("GestionAutores");
        //request.getRequestDispatcher("GestionAutores").forward(request, response);
    }
}