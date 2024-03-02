package com.biblioteca.controladores.empleado;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.servicios.ServicioPrestamo;
import com.biblioteca.servicios.ServicioUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "RealizarPrestamo", urlPatterns = {"/empleado/RealizarPrestamo"})
public class RealizarPrestamo extends HttpServlet {

    public RealizarPrestamo() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/empleado/realizarPrestamo.jsp";
        //mostar una lista con los libros disponibles para prestar (unidades > 0)
        List<Libro> libros = ServicioLibro.listarLibrosDisponiblesParaPrestar();
        request.setAttribute("libros", libros);
        //mostrar una lista con los socios NO sancionados
        List<Usuario> socios = ServicioUsuario.listarSociosNoSancionados();
        request.setAttribute("socios", socios);
        request.getRequestDispatcher(vista).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/empleado/realizarPrestamo.jsp";
        //recoger los datos del formulario
        String socioId = request.getParameter("socio");
        String libroId = request.getParameter("libro");
        if (socioId != null && libroId != null && !socioId.isEmpty() && !libroId.isEmpty()) {
            Libro libro = ServicioLibro.buscarLibroPorId(Integer.parseInt(libroId));
            Usuario socio = ServicioUsuario.buscarUsuarioPorId(Integer.parseInt(socioId));

            //realizar el prestamo
            Prestamo prestamo = new Prestamo(LocalDate.now(), libro, socio);
            ServicioPrestamo.insertarPrestamo(prestamo);
            //decrementar el numnero de ejemplares disponibles
            libro.setNumeroEjemplares(libro.getNumeroEjemplares() - 1);
            ServicioLibro.actualizarLibro(libro);

            List<Prestamo> prestamosSocio = ServicioPrestamo.listarPrestamosPorUsuario(socio.getId());
            List<Prestamo> prestamosLibro = ServicioPrestamo.listarPrestamosPorLibro(libro.getId());

            request.setAttribute("libroPrestado", libro);
            request.setAttribute("socio", socio);
            request.setAttribute("prestamo", prestamo);
            request.setAttribute("prestamosSocio", prestamosSocio);
            request.setAttribute("prestamosLibro", prestamosLibro);
            request.getRequestDispatcher("/empleado/infoPrestamo.jsp").forward(request, response);
            return;
        }
        request.setAttribute("error", "No se ha podido realizar el prestamo");
        request.getRequestDispatcher(vista).forward(request, response);
    }
}
