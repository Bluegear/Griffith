package org.bandofhawk.griffith.dao;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * Created by Bluegear on 5/31/14.
 */
public class CommonDAO {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    public CommonDAO() {
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
