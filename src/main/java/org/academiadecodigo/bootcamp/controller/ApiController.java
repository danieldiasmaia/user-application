package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;
import org.academiadecodigo.bootcamp.service.user.MockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;


/**
 * Created by codecadet on 22/07/16.
 */

@RestController
public class ApiController {

    @Autowired
    private HibernateUserService userService;

    private User user;


    @RequestMapping(method = RequestMethod.GET, value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkedList<User>> listUsers(){

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "api/user/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("name") String name){

        User user = null;
        try {
            user = userService.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user != null){

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(method = RequestMethod.POST, value = "api/user/add/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody User user){

        userService.addUser(user);


        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "api/user/edit/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("name") String name, @RequestBody User user){


        try {
            this.user = userService.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.user.setName(user.getName());
        this.user.setPassword(user.getPassword());
        this.user.setEmail(user.getEmail());

        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "api/user/delete/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("name") String name){

        User user = null;
        try {
            user = userService.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user != null){

            userService.deleteUser(name);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
