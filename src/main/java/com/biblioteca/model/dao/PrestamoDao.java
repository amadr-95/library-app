package com.biblioteca.model.dao;

import com.biblioteca.model.entidades.Prestamo;
import com.biblioteca.model.entidades.Usuario;
import com.biblioteca.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class PrestamoDao {

    public PrestamoDao() {
    }

    /*public List<Prestamo> listarPrestamosPorUsuario(Usuario usuario){
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT p FROM Prestamo p WHERE p.usuario = :usuario";
        List<Prestamo> prestamos = entityManager.createQuery(query, Prestamo.class)
                .setParameter("usuario", usuario)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return prestamos;
    }*/

    public void establecerFechaDevolucion(long prestamoId, LocalDate fechaDevolucion){
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Prestamo prestamo = entityManager.find(Prestamo.class, prestamoId);
        prestamo.setFechaDevolucion(fechaDevolucion);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Prestamo> listarPrestamosPorUsuario(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT p FROM Prestamo p WHERE p.usuario.id = :id";
        List<Prestamo> prestamos = entityManager.createQuery(query, Prestamo.class)
                .setParameter("id", id)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return prestamos;
    }

    public List<Prestamo> listarPrestamosCompletadosPorUsuario(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        String query = "SELECT p FROM Prestamo p WHERE p.usuario.id = :id AND p.fechaDevolucion IS NOT NULL";
        List<Prestamo> prestamos = entityManager.createQuery(query, Prestamo.class)
                .setParameter("id", id)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return prestamos;
    }

    public Prestamo obtenerPrestamoPorId(long id){
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Prestamo prestamo = entityManager.find(Prestamo.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return prestamo;
    }
}
