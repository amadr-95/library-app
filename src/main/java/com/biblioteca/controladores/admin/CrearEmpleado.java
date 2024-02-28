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

@WebServlet(name = "CrearEmpleado", urlPatterns = {"/admin/CrearEmpleado"})
public class CrearEmpleado extends HttpServlet {

    public CrearEmpleado() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/crearEmpleado.jsp";
        request.setAttribute("roles", Rol.values());
        request.getRequestDispatcher(vista).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/admin/crearEmpleado.jsp";
        request.setAttribute("roles", Rol.values());
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos"); //puede estar vacio (opcional)
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Rol rol = Rol.valueOf(request.getParameter("rol"));
        //comprobar que los datos no sean nulos ni esten vacios
        if (
                nombre != null && apellidos != null && email != null && password != null &&
                        !nombre.trim().isEmpty() && !email.trim().isEmpty() && !password.trim().isEmpty()
        ) {
            //comprobar que el email no esta en uso
            Usuario usuario = ServicioUsuario.buscarUsuarioPorEmail(email);
            if (usuario != null) {
                request.setAttribute("error", "El email ya esta en uso");
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                //guardar el usuario en la base de datos
                Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, password, rol);
                ServicioUsuario.insertarUsuario(nuevoUsuario);
                //redirigir a la pagina de gestion de empleados
                response.sendRedirect("GestionEmpleados");
            }
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}