package org.academiadecodigo.bootcamp.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by dmaia on 16-04-2017.
 */
public final class HibernateSessionManager {

    private static HibernateSessionManager instance;
    private static SessionFactory sessionFactory;

    private HibernateSessionManager(){
        try {

            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("persistence/hibernate.cfg.xml") // Load settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata() // Tell Hibernate about sources of metadata (database mappings)
                    .buildSessionFactory();

        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError("Error creating hibernate session factory: " + ex.getMessage());
        }
    }

    public static synchronized HibernateSessionManager getInstance(){

        if(instance == null){
            instance = new HibernateSessionManager();
        }

        return instance;
    }

    public Session beginTransaction(){

        Session s = getSession();
        s.beginTransaction();

        return s;

    }

    public void commitTransaction(){

        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    public void rollbackTransaction(){

        sessionFactory.getCurrentSession().getTransaction().rollback();

    }



    public Session getSession() {
        // Hibernate will automatically open a new session if needed
        // Closing the session is not required
        return sessionFactory.getCurrentSession();
    }

    // Required to stop hibernate and allow the application to terminate
    public void close() {
        sessionFactory.close();
    }


}
