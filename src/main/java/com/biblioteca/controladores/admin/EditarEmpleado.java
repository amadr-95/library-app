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

@WebServlet(name = "EditarEmpleado", urlPatterns = {"/admin/EditarEmpleado"})
public class EditarEmpleado extends HttpServlet {

    public EditarEmpleado() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //cuando accedemos por GET debemos mostrar el formulario con los datos del usuario
        String vista = "/admin/editarEmpleado.jsp";
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long empleadoId = Long.parseLong(id);
            //buscamos el usuario por su id
            Usuario usuario = ServicioUsuario.buscarUsuarioPorId(empleadoId);
            if (usuario != null) {
                request.setAttribute("usuarioEditar", usuario);
                request.setAttribute("roles", Rol.values());
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                response.sendRedirect("GestionEmpleados");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String vista = "/admin/editarEmpleado.jsp";

        long id = Long.parseLong(request.getParameter("id"));
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
            if (usuario != null && usuario.getId() != id) {
                //creo que esto es necesario por si falla el formulario
                request.setAttribute("roles", Rol.values());
                request.setAttribute("error", "El email ya esta en uso");
                request.setAttribute("usuarioEditar", new Usuario(id, nombre, apellidos, email, password, rol));
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                //actualizar el usuario en la base de datos
                Usuario usuarioActualizado = new Usuario(id, nombre, apellidos, email, password, rol);
                ServicioUsuario.actualizarUsuario(usuarioActualizado);
                //redirigir a la pagina de gestion de empleados
                response.sendRedirect("GestionEmpleados");
            }
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }

}
