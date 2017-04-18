package org.academiadecodigo.bootcamp.service.user;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.persistence.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmaia on 16-04-2017.
 */
@Service
public class HibernateUserService implements UserService {

    @Override
    public boolean authenticate(String name, String password) {
        boolean isAuthenticated = false;

        try {

            Session session = HibernateSessionManager.getInstance().beginTransaction();

            Query q = session.createQuery("from User where username = '" + name + "' and password = '" + password + "'");

            //HibernateSessionManager.getInstance().commitTransaction();

            if(q.uniqueResult() != null){
                isAuthenticated = true;
            }

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateSessionManager.getInstance().rollbackTransaction();

        }

        return isAuthenticated;
    }

    @Override
    public void addUser(User user) {

        try {

            Session session = HibernateSessionManager.getInstance().beginTransaction();

            session.save(user);

            HibernateSessionManager.getInstance().commitTransaction();

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateSessionManager.getInstance().rollbackTransaction();

        }

    }

    @Override
    public User findByName(String name) throws SQLException {

        try {

            Session session = HibernateSessionManager.getInstance().beginTransaction();



            Query q = session.createQuery("from User where name = '" + name + "'");


            //HibernateSessionManager.getInstance().commitTransaction();

            User u = (User) q.uniqueResult();

            return u;

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateSessionManager.getInstance().rollbackTransaction();

        }

        return null;

    }

    @Override
    public void deleteUser(String user) {

    }

    @Override
    public List<User> findAll() {

        List<User> users;

        try {

            Session session = HibernateSessionManager.getInstance().beginTransaction();



            Query q = session.createQuery("from User");

            users = q.list();
            //HibernateSessionManager.getInstance().commitTransaction();

            //User u = (User) q.uniqueResult();

            return users;

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateSessionManager.getInstance().rollbackTransaction();

        }

        return null;
    }


    @Override
    public int count() throws SQLException {

        String query = "select count username from users";

        try {

            Session session = HibernateSessionManager.getInstance().beginTransaction();


            Query q = session.createQuery("from User");
            java.util.List list = q.list();
            int result = list.size();

            HibernateSessionManager.getInstance().commitTransaction();

            return result;

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateSessionManager.getInstance().rollbackTransaction();

        }

        return 0;

    }
}
