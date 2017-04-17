package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.persistence.HibernateSessionManager;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

/**
 * Created by Daniel Maia on 17/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        HibernateUserService serv = new HibernateUserService();



        try {
            User user = serv.findByName("dmaia");
            System.out.println(user.getName());
            System.out.println(user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println("lol");
    }

}

