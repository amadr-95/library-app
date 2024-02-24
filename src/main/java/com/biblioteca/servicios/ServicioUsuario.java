package com.biblioteca.servicios;

import com.biblioteca.model.dao.UsuarioDao;
import com.biblioteca.model.entidades.Rol;
import com.biblioteca.model.entidades.Usuario;
import jakarta.persistence.NoResultException;

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
        return usuarioDao.listarUsuariosPorRol(rol);
    }
}
