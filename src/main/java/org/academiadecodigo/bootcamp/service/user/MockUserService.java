package org.academiadecodigo.bootcamp.service.user;

import org.academiadecodigo.bootcamp.model.User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 22/06/16.
 */
public class MockUserService implements UserService {

    private List<User> users = new LinkedList<User>();

    public MockUserService(){
        makeList();
    }

    @Override
    public boolean authenticate(String name, String password) {


        Iterator<User> it = users.iterator();

        while(it.hasNext()){

            User user = it.next();

            if(user.getName().equals(name) && user.getPassword().equals(password)){
                return true;
            }

        }

        return false;
    }

    @Override
    public void addUser(User user){
        users.add(user);
    }

    @Override
    public void deleteUser(String user){
        users.remove(findByName(user));
    }

    @Override
    public List<User> findAll(){
        return users;
    }

    @Override
    public User findByName(String user1){

        Iterator<User> it = users.iterator();

        while(it.hasNext()){

            User user = it.next();

            if(user.getName().equals(user1)){
                return user;
            }

        }

        return null;
    }

    @Override
    public int count() throws SQLException {
        return 0;
    }

    public void makeList() {

        User user = new User();
        user.setName("daniel");
        user.setPassword("daniel");
        user.setEmail("daniel@daniel.com");
        users.add(user);

        user = new User();
        user.setName("ana");
        user.setPassword("ana");
        user.setEmail("ana@ana.com");
        users.add(user);

        user = new User();
        user.setName("joao");
        user.setPassword("joao");
        user.setEmail("joao@joao.com");
        users.add(user);

        user = new User();
        user.setName("david");
        user.setPassword("david");
        user.setEmail("david@david.com");
        users.add(user);

    }
}
