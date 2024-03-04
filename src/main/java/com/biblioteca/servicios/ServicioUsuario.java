package com.biblioteca.servicios;

import com.biblioteca.model.dao.UsuarioDao;
import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Rol;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.util.PrestamoUtil;
import jakarta.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {

    private static final UsuarioDao usuarioDao = new UsuarioDao();


    public static Usuario autenticar(String email, String password) {
        //Accedemos a la base de datos y comprobamos si el usuario y la contraseña son correctos
        try {
            return usuarioDao.autenticar(email, password);
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void insertarUsuario(Usuario usuario) {
        usuarioDao.insertarUsuario(usuario);
    }

    public static Usuario buscarUsuarioPorEmail(String email) {
        try {
            return usuarioDao.buscarUsuarioPorEmail(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<Usuario> listarUsuariosPorRol(Rol rol) {
        return usuarioDao.listarUsuarios(rol);
    }

    public static List<Usuario> listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public static List<Usuario> listarEmpleados() {
        return usuarioDao.listarEmpleados();
    }

    public static void eliminarUsuario(long empleadoId) {
        usuarioDao.eliminarUsuario(empleadoId);
    }

    public static Usuario buscarUsuarioPorId(long id) {
        return usuarioDao.buscarUsuarioPorId(id);
    }

    public static void actualizarUsuario(Usuario usuario) {
        usuarioDao.actualizarUsuario(usuario);
    }

    public static List<Usuario> listarSociosNoSancionados() {
        List<Usuario> socios = usuarioDao.listarUsuarios(Rol.SOCIO);
        List<Usuario> sociosNoSancionados = new ArrayList<>();

        for (Usuario socio : socios) {
            boolean sancionado = false;
            List<Prestamo> prestamos = socio.getPrestamos();

            for (Prestamo prestamo : prestamos) {
                if (!PrestamoUtil.prestamoVigente(prestamo.getId())) {
                    sancionado = true;
                    break;
                }
            }

            if(!sancionado)
                sociosNoSancionados.add(socio);
        }
        return sociosNoSancionados;
    }

    public static List<Usuario> listarSociosSancionados() {
        List<Usuario> socios = usuarioDao.listarUsuarios(Rol.SOCIO);
        List<Usuario> sociosSancionados = new ArrayList<>();

        for (Usuario socio : socios) {
            boolean sancionado = false;
            List<Prestamo> prestamos = socio.getPrestamos();

            for (Prestamo prestamo : prestamos) {
                if (!PrestamoUtil.prestamoVigente(prestamo.getId()) && prestamo.getFechaDevolucion() == null) {
                    sancionado = true;
                    break;
                }
            }
            if(sancionado)
                sociosSancionados.add(socio);
        }
        return sociosSancionados;
    }
}
