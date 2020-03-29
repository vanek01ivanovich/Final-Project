package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.Binding;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/save")
    public ModelAndView saveUser(User user, Model model, RedirectAttributes redirectAttrs){
        System.out.println(user.getUserName());
        ModelAndView modelAndView;
        user.setRole(RoleStatus.ROLE_USER);
        if(userServiceImpl.existsUserByUserName(user.getUserName())){
            System.out.println("exists");
            Integer al = 0;
            redirectAttrs.addFlashAttribute("alert",al);
            return new ModelAndView("redirect:/registration");
        }else {
            System.out.println("not exist");
            Integer al = 1;
            redirectAttrs.addFlashAttribute("alert",al);
            userServiceImpl.addUser(user);
            return new ModelAndView("redirect:/login");
        }

    }






}
