package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/update/check")
public class UpdateController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public UpdateController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping("/save")
    public ModelAndView saveUser(User user, Model model, RedirectAttributes redirectAttrs){
        System.out.println(user.getUserName());
        ModelAndView modelAndView;
        System.out.println(user.getUserName());
        //user.setRole(RoleStatus.ROLE_USER);
        if(userServiceImpl.existsUserByUserName(user.getUserName())){
            System.out.println("exists");
            Integer al = 0;
            redirectAttrs.addFlashAttribute("upd",al);
            redirectAttrs.addFlashAttribute("user",user);
            return new ModelAndView("redirect:/admin/allusers/update");
        }else {
            System.out.println("not exist");
            Integer al = 1;
            redirectAttrs.addFlashAttribute("upd",al);
            //userServiceImpl.addUser(user);
            return new ModelAndView("redirect:/admin/allusers");
        }

    }
}
