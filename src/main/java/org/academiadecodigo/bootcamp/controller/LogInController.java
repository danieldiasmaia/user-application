package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.service.user.HibernateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by codecadet on 15/07/16.
 */
@Controller
@SessionAttributes("loginUser")
public class LogInController {

    @Autowired
    private HibernateUserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView showLogin() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginUser", new User());
        return modelAndView;

    }


    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String doLogin(Model model, @Valid @ModelAttribute("loginUser") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "login";
        } else if(userService.authenticate(user.getName(), user.getPassword())){
            return "redirect:/users";
        } else {
            model.addAttribute("error", "Authentication Failure");
            return "login";
        }

    }
}
