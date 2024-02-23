package com.biblioteca.controladores;

import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    public Login() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar la página de inicio de sesión (login.jsp)
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "login.jsp";
        // Obtener las credenciales del formulario de inicio de sesión
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (
                email != null && password != null &&
                        !email.trim().isEmpty() && !password.trim().isEmpty()
        ) {
            Usuario usuario = ServicioUsuario.autenticar(email, password);
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("MenuPrincipal");
                return;
            } else {
                request.setAttribute("error", "Email o contraseña incorrectos. Inténtalo de nuevo");
            }
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}