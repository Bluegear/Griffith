package org.bandofhawk.griffith.dao;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * Griffith
 * Created by Bluegear on 5/31/14.
 */
public class CommonDAO {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    private static CommonDAO instance = new CommonDAO();

    public EntityManager getEntityManager(){
        return em;
    }

    public static CommonDAO getInstance(){
        return instance;
    }

    private CommonDAO() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
        }
    }

    @Transactional
    public void save(Object obj) {
        em.persist(obj);
    }
}
