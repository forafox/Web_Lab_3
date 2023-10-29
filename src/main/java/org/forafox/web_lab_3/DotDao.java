package org.forafox.web_lab_3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class DotDao implements Serializable{
    @PersistenceContext(unitName = "dots")
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    public void createEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("dots");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addDotToDB(Dot dot) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(dot);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
    }

    public List<Dot> getDotsFromDB() {
        return entityManager.createQuery("select s from Dot s", Dot.class).getResultList();
    }

    public void clearDotsInBD() {
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("delete from Dot").executeUpdate();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
    }
}
