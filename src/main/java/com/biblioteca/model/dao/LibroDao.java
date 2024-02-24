package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LibroDao {

    public LibroDao() {
    }

    public List<Libro> listarLibros() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT l FROM Libro l";
        List<Libro> libros = entityManager.createQuery(query, Libro.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return libros;
    }

    public void insertarLibro(Libro libro) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(libro);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
