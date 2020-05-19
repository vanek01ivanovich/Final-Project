package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        String oldUserName = (String)model.asMap().get("userName");

        if(userServiceImpl.existsUserByUserName(user.getUserName()) && !(oldUserName.equals(user.getUserName()))){

            redirectAttrs.addFlashAttribute("upd",0);
            redirectAttrs.addFlashAttribute("user",user);
            redirectAttrs.addFlashAttribute("oldUserName",oldUserName);
            return new ModelAndView("redirect:/admin/allusers/update");
        }else {


            redirectAttrs.addFlashAttribute("upd",1);
            userServiceImpl.updateUser(user,oldUserName);
            return new ModelAndView("redirect:/admin/allusers");
        }

    }
}
