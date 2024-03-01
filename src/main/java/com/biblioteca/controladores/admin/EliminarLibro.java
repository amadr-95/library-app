package com.biblioteca.controladores.admin;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.servicios.ServicioLibro;
import com.biblioteca.util.LibroUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminarLibro", urlPatterns = {"/admin/EliminarLibro"})
public class EliminarLibro extends HttpServlet {

    public EliminarLibro() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long libroId = Long.parseLong(id);
            //Eliminamos el libro de la base de datos, eliminando también los registros de la tabla
            //libro_autor y libro_genero que tengan el id del libro, siempre y cuando no tenga prestamos activos
            Libro libro = ServicioLibro.buscarLibroPorId(libroId);
            if (libro != null) {
                for(Prestamo p : libro.getPrestamos()) {
                    //Si alguna fecha es null, no se puede eliminar porque tiene prestamos activos
                    if(p.getFechaDevolucion() == null) {
                        request.setAttribute("error", "No se puede eliminar el libro porque tiene préstamos activos");
                        request.getRequestDispatcher("GestionLibros").forward(request, response);
                        return;
                    }
                }

                if(!libro.getPrestamos().isEmpty()) {
                    request.setAttribute("error", "No se puede eliminar el libro porque está en el historial de" +
                            " prestamos de un uusario");
                    request.getRequestDispatcher("GestionLibros").forward(request, response);
                    return;
                }

                //si llegamos aqui, podemos eliminar el libro
                String imagenPortada = libro.getImagenPortada();
                ServicioLibro.eliminarLibro(libroId);
                LibroUtils.eliminarArchivo(getServletContext().getRealPath("/img"), imagenPortada);
                response.sendRedirect("GestionLibros");

                /*if (libro.getPrestamos().isEmpty()) {
                    String imagenPortada = libro.getImagenPortada();
                    ServicioLibro.eliminarLibro(libroId);
                    LibroUtils.eliminarArchivo(getServletContext().getRealPath("/img"), imagenPortada);
                    response.sendRedirect("GestionLibros");
                    return;
                } else {
                    request.setAttribute("error", "No se puede eliminar el libro porque tiene préstamos activos");
                }*/
            }
        }
        //request.getRequestDispatcher("GestionLibros").forward(request, response);
    }
}
