package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Genero;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GeneroDao {

    public GeneroDao() {
    }

    public List<Genero> listarGeneros() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT g FROM Genero g";
        List<Genero> generos = entityManager.createQuery(query, Genero.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return generos;
    }

    public Genero buscarGeneroPorId(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Genero genero = entityManager.find(Genero.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return genero;
    }
}
