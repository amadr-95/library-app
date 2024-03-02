package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Libro;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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

    public List<Libro> listarLibros(String filtro) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT l FROM Libro l WHERE l.titulo LIKE :filtro";
        List<Libro> libros = entityManager.createQuery(query, Libro.class)
                .setParameter("filtro", "%" + filtro + "%")
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

    public Libro buscarLibroPorIsbn(String isbn) throws NoResultException {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT l FROM Libro l WHERE l.isbn = :isbn";
        Libro libro = entityManager.createQuery(query, Libro.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return libro;
    }

    public Libro buscarLibroPorId(long id) throws NoResultException {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT l FROM Libro l WHERE l.id = :id";
        Libro libro = entityManager.createQuery(query, Libro.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.close();
        return libro;
    }

    public void eliminarLibro(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "DELETE FROM Libro l WHERE l.id = :id";
        entityManager.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void actualizarLibro(Libro libro) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(libro);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Libro> listarLibrosDisponiblesParaPrestar() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT l FROM Libro l WHERE l.numeroEjemplares > 0";
        List<Libro> libros = entityManager.createQuery(query, Libro.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return libros;
    }
}
