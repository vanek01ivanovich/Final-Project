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


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user,Model model){

        model.addAttribute("user", user);
        userName =  (String)model.asMap().get("oldUserName");

        if (userName == null) {
            userName = user.getUserName();
        }

        getCurrentLocale(model);


        return "updateUser";
    }


    @GetMapping("/update")
    public String updateUser(User user,Model model){

            model.addAttribute("user", user);
            userName =  (String)model.asMap().get("oldUserName");

            if (userName == null) {
                userName = user.getUserName();
            }

        getCurrentLocale(model);


        return "updateUser";
    }



    @RequestMapping(value = "/update/check")
    public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            getCurrentLocale(model);
            model.addAttribute("updateSuccess",0);
            return "updateUser";
        }

        getCurrentLocale(model);

        System.out.println(user.getUserName());
        model.addAttribute("updateSuccess",1);
        redirectAttrs.addFlashAttribute("user",user);
        redirectAttrs.addFlashAttribute("userName",userName);
        return "redirect:/update/check/save";
    }





    @Transactional
    @GetMapping("/delete")
    public String  deleteUser(User user,Model model){
        userServiceImpl.deleteUser(user);
        return "redirect:/admin/allusers";

    }

    private void getCurrentLocale(Model model){
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

    }


}
