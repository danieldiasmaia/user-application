package org.academiadecodigo.bootcamp.service.user;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.service.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmaia on 16-04-2017.
 */
public interface UserService extends Service {

    boolean authenticate(String userName, String password);

    void addUser(User user);

    User findByName(String userName) throws SQLException;

    void deleteUser(String user);

    List<User> findAll();

    int count() throws SQLException;

}
