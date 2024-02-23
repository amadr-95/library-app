package com.biblioteca.controladores;

import com.biblioteca.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MenuPrincipal", value = "/MenuPrincipal")
public class MenuPrincipal extends HttpServlet {

    public MenuPrincipal() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String vista = "/menuPrincipal.jsp";
        Usuario usuario = (Usuario) (request.getSession().getAttribute("usuario"));
        if (usuario == null) {
            response.sendRedirect("Login");
            return;
        }
        //averiguamos el rol del usuario
        switch (usuario.getRol()) {
            case ADMIN:
                request.setAttribute("admin", true);
                break;
            case EMPLEADO:
                request.setAttribute("empleado", true);
                break;
            case SOCIO:
                request.setAttribute("socio", true);
                break;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }
}