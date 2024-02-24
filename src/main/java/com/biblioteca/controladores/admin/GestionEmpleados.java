package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Rol;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GestionEmpleados", urlPatterns = {"/admin/GestionEmpleados"})
public class GestionEmpleados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/gestionEmpleados.jsp";
        //Devolvemos una lista con todos los usuarios que tienen el Rol de EMPLEADO
        List<Usuario> listaEmpleados = ServicioUsuario.listarEmpleados();
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.getRequestDispatcher(vista).forward(request, response);
    }
}

