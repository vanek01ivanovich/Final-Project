package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(value = "/admin/allusers")
public class AdminController {

    private UserServiceImpl userServiceImpl;
    private String userName;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/update")
    public String updateUser(User user,Model model){

        model.addAttribute("user", user);
        userName =  (String)model.asMap().get("oldUserName");

        if (userName == null) {
            userName = user.getUserName();
        }


        userServiceImpl.getLocale(model);

        return "updateUser";
    }



    @RequestMapping(value = "/update/check")
    public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            userServiceImpl.getLocale(model);
            return "updateUser";
        }
        userServiceImpl.getLocale(model);
        redirectAttrs.addFlashAttribute("user",user);
        redirectAttrs.addFlashAttribute("userName",userName);
        return "redirect:/update/check/save";
    }


    @Transactional
    @GetMapping("/delete")
    public String  deleteUser(User user){
        userServiceImpl.deleteUser(user);
        return "redirect:/admin/allusers";

    }



}
