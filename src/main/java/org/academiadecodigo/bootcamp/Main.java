package org.academiadecodigo.bootcamp;


import org.academiadecodigo.bootcamp.controller.UserController;
import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.persistence.HibernateSessionManager;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel Maia on 17/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        UserController uc = new UserController();


        Model model = new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };

        System.out.println(uc.listUsers(model));

        //System.out.println("lol");
    }

}

