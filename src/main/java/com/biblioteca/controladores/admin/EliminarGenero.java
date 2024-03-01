package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Genero;
import com.biblioteca.servicios.ServicioGenero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminarGenero", urlPatterns = {"/admin/EliminarGenero"})
public class EliminarGenero extends HttpServlet {

    public EliminarGenero() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long generoId = Long.parseLong(id);
            //comprobamos que el genero no tenga libros asociados
            Genero genero = ServicioGenero.buscarGeneroPorId(generoId);
            if (genero != null) {
                if (genero.getLibros().isEmpty()){
                    //Eliminamos el genero de la base de datos
                    ServicioGenero.eliminarGenero(genero);
                    response.sendRedirect("GestionGeneros");
                    return;
                } else{
                    request.setAttribute("error", "No se puede eliminar el género porque tiene libros asociados");
                }
            }
        }
        request.getRequestDispatcher("GestionGeneros").forward(request, response);
    }
}