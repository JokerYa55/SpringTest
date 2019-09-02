/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public class dbDatabase {

    private final EntityManagerFactory emf;
    private static final Logger LOG = Logger.getLogger(dbDatabase.class.getName());

    public dbDatabase() {
        emf = Persistence.createEntityManagerFactory("db_JPA");
    }

    public EntityManager getEM() {
        return emf.createEntityManager();
    }

}
