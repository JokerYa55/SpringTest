/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Country;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public class CountryDao {

    private static final Logger LOG = Logger.getLogger(CountryDao.class.getName());
    private EntityManager em;
    @Autowired
    private dbDatabase db;

    
    public CountryDao() {
        em = db.getEM();
    }

    public Country findById(String id) {
        return em.find(Country.class, id);
    }

}
