package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class MainController {

    @Autowired
    public static ResourceBundle resourceBundle;

    @GetMapping("/users")
    public String users(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();

        model.addAttribute("username",user.getUsername());
        model.addAttribute("userrole",user.getAuthorities());

        return "user";
    }


    @GetMapping("/admin")
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();



        model.addAttribute("username",user.getUsername());
        model.addAttribute("userrole",user.getAuthorities());

        return "admin";
    }

    @GetMapping("/registration")
    public String registration(Model model){

        return "registration";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error",required = false) String error,
                           @RequestParam(value = "logout",required = false) String logout,
                           Model model){
        //resourceBundle = ResourceBundle.getBundle("context",Locale.ENGLISH);
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);

        model.addAttribute("error",error != null);
        model.addAttribute("logout",logout != null);
        return "login";
    }


    /*public void callme(){
        System.out.println("hi");
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("ukr"));
        //slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }
*/
    /*@Bean
    public LocaleResolver localeResolverEN() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //slr.setDefaultLocale(new Locale("ukr"));
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }*/

}
