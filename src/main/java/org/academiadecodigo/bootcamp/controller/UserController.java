package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by codecadet on 20/07/16.
 */
@Controller
@SessionAttributes("loginUser")
public class UserController {

    @Autowired
    private HibernateUserService userService;

    private User user;

    private boolean onUpdate;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String listUsers(Model model){

        if(onUpdate){

            model.addAttribute(Attribute.USER, user);

        } else {

            User user = new User();
            model.addAttribute(Attribute.USER, user);
        }



        model.addAttribute(Attribute.USER_LIST, userService.findAll());
        return "users";
    }

    /*
    @ModelAttribute(method = , value =)
    public User populateLogin(){
        return new User();
    }
    */

    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public String addUser(Model model, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {



        if (onUpdate) {

            this.user.setName(user.getName());
            this.user.setPassword(user.getPassword());
            this.user.setEmail(user.getEmail());

            onUpdate = false;

        } else {

            try {
                if (user.getName().isEmpty() || user.getName() == null || userService.findByName(user.getName()) != null) {
                    return "redirect:/users";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            userService.addUser(user);


        }


        redirectAttributes.addFlashAttribute(Attribute.ADD_MESSAGE,
                "Added user " + user.getName() + " successfully!");

        return "redirect:/users";

    }

    @RequestMapping (method = RequestMethod.GET, value = "/user/delete/{username}")
    public String deleteUser(Model model, @PathVariable("username") String username, RedirectAttributes redirectAttributes){

        userService.deleteUser(username);

        redirectAttributes.addFlashAttribute(Attribute.DELETE_MESSAGE,
                "Removed user " + username + " successfully!");

        return "redirect:/users";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/user/edit/{username}")
    public String editUser(Model model, @PathVariable("username") String username, RedirectAttributes redirectAttributes){

        onUpdate = true;

        try {
            this.user = userService.findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/users";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/logout")
    public String logout(@ModelAttribute( "users") LinkedList<User> users, RedirectAttributes redirectAttributes){

        onUpdate = false;
        redirectAttributes.addFlashAttribute("users", users);

        return "redirect:/";
    }




}
