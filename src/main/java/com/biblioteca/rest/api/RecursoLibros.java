package com.biblioteca.rest.api;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.servicios.ServicioLibro;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/libros")
public class RecursoLibros {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLibros() {
        List<Libro> libros = ServicioLibro.listarLibros();
        return Response.ok()
                .status(Response.Status.OK)
                .entity(libros)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarLibroPorId(@PathParam("id") long id) {
        Libro libro = ServicioLibro.buscarLibroPorId(id);
        if(libro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok()
                    .status(Response.Status.OK)
                    .entity(libro)
                    .build();
        }
    }
}
