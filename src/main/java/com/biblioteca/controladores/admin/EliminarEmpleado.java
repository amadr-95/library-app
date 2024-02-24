package com.biblioteca.controladores.admin;

import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminarEmpleado", urlPatterns = {"/admin/EliminarEmpleado"})
public class EliminarEmpleado extends HttpServlet {

    public EliminarEmpleado() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/gestionEmpleados.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long empleadoId = Long.parseLong(id);
            //Eliminamos el usuario de la base de datos
            ServicioUsuario.eliminarUsuario(empleadoId);
            response.sendRedirect("GestionEmpleados");
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}