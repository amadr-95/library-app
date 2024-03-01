package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Autor;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AutorDao {

    public AutorDao() {
    }

    public static List<Autor> listarAutores() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT a FROM Autor a";
        List<Autor> autores = entityManager.createQuery(query, Autor.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return autores;
    }

    public Autor buscarAutorPorId(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Autor autor = entityManager.find(Autor.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return autor;
    }

    public void insertarAutor(Autor autor) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(autor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Autor buscarAutorPorNombre(String nombre) throws NoResultException {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT a FROM Autor a WHERE a.nombre = :nombre";
        Autor autor = entityManager.createQuery(query, Autor.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return autor;
    }

    public void eliminarAutor(Autor autor) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(autor) ? autor : entityManager.merge(autor));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
