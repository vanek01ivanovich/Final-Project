package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
            //userServiceImpl.addUser(user);

            return new ModelAndView("redirect:/login");
        }
        //


    }

    /*@RequestMapping(value = "/save")
    public @ResponseBody
    ResponseEntity<User> saveUser(User user){
        user.setRole(RoleStatus.ROLE_USER);
        if(userServiceImpl.existsUserByUserName(user.getUserName())){
            System.out.println("exists");
            return
        }else {
            System.out.println("not exist");
        }
        //userServiceImpl.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

}
