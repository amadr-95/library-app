package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UsuarioDao {

    public UsuarioDao() {
    }

    public Usuario autenticar(String email, String password) throws NoResultException {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password";

        Usuario usuarioLogueado = entityManager.createQuery(query, Usuario.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return usuarioLogueado;
    }
}
