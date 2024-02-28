package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Genero;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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

    public Genero buscarGeneroPorNombre(String nombre) throws NoResultException {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT g FROM Genero g WHERE g.genero = :nombre";
        Genero genero = entityManager.createQuery(query, Genero.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return genero;
    }

    public void insertarGenero(Genero genero) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(genero);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
