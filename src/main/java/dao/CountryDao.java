/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Country;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public class CountryDao {

    private static final Logger LOG = Logger.getLogger(CountryDao.class.getName());
    //private EntityManager em;
    @Autowired
    private DbDatabase db;

    public CountryDao() {
        LOG.info("CountryDao = ");
    }

    public Country findById(String id) {
        EntityManager em = db.getEM();
        return em.find(Country.class, id);
    }

    public void addItem(Country item) {
        EntityManager em = null;
        try {
            em = db.getEM();
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
