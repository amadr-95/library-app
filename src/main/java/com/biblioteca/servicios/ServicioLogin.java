package com.biblioteca.servicios;

import com.biblioteca.model.dao.UsuarioDao;
import com.biblioteca.model.entidades.Usuario;
import jakarta.persistence.NoResultException;

public class ServicioLogin {

    public static Usuario autenticar(String email, String password) {
        //Accedemos a la base de datos y comprobamos si el usuario y la contraseña son correctos
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.autenticar(email, password);
        } catch (NoResultException e) {
            return null;
        }
    }
}
