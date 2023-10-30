package org.forafox.web_lab_3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class DotDao implements Serializable {
    private EntityManager entityManager;
    public void createEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("dots").createEntityManager();
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
        return entityManager.createQuery("select dots from Dot dots", Dot.class).getResultList();
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
