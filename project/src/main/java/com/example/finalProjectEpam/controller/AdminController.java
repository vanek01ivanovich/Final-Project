package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/allusers")
public class AdminController {

    private UserServiceImpl userServiceImpl;
    ModelAndView modelAndView = new ModelAndView();





    @GetMapping("/update")
    public String updateUser(User user,Model model){

            model.addAttribute("user", user);
            System.out.println(user.getUserName());
            //modelAndView.addObject("users",user);


        return "updateUser";
    }

    /*@RequestMapping(value = "/update",method = RequestMethod.POST)
    public String d(User user){
        System.out.println(user.getUserName());
        return "updateUser";
    }*/

    @RequestMapping(value = "/update/check")
    public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {

            model.addAttribute("updateSuccess",0);


            return "updateUser";
        }
        System.out.println(user.getUserName());
        model.addAttribute("updateSuccess",1);
        redirectAttrs.addFlashAttribute("user",user);
        return "redirect:/update/check/save";
    }




    @RequestMapping("/delete")
    public @ResponseBody String deleteUser(User user){
        return user.getUserName();
    }


}
