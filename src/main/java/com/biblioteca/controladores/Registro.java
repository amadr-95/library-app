package com.biblioteca.controladores;

import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Registro", value = "/Registro")
public class Registro extends HttpServlet {

    public Registro() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar la página de inicio de sesión (login.jsp)
        request.getRequestDispatcher("/registro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "registro.jsp";
        //Obtener los datos del registro
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos"); //puede estar vacio (opcional)
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        //comprobar que los datos no sean nulos ni esten vacios
        if (
                nombre != null && apellidos != null && email != null && password != null && password2 != null &&
                        !nombre.trim().isEmpty() && !email.trim().isEmpty() &&
                        !password.trim().isEmpty() && !password2.trim().isEmpty()
        ) {
            //comprobar que las contraseñas coinciden
            if (!password.equals(password2)) {
                request.setAttribute("error", "Las contraseñas no coinciden");
                request.getRequestDispatcher(vista).forward(request, response);
                return;
            }
            //comprobar que el email no esta en uso
            Usuario usuario = ServicioUsuario.buscarUsuarioPorEmail(email);
            if (usuario != null) {
                request.setAttribute("error", "El email ya esta en uso");
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                //guardar el usuario en la base de datos con rol de socio por defecto
                Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, password);
                ServicioUsuario.insertarUsuario(nuevoUsuario);
                //redirigir a la pagina de inicio de sesion
                response.sendRedirect("Login");
            }
            return;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
