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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

        Locale locale = LocaleContextHolder.getLocale();

        if (locale == Locale.ENGLISH){
            String userName = user.getFirstName()+" "+user.getLastName();
            model.addAttribute("username",userName);
        }else {
            String userName = user.getFirstNameUkr()+" "+user.getLastNameUkr();
            model.addAttribute("username",userName);
        }

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

   @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            model.addAttribute("redir",0);
            return "registration";
        }
       model.addAttribute("redir",1);
       redirectAttrs.addFlashAttribute("user",user);
       return "redirect:/registration/save";
    }

    @GetMapping("/registration")
    public String registrationSubmit(Model model){
        User user = new User();
       model.addAttribute("user",user);
        return "registration";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error",required = false) String error,
                           @RequestParam(value = "logout",required = false) String logout,
                           Model model){
        //resourceBundle = ResourceBundle.getBundle("context",Locale.ENGLISH);
        Locale locale = LocaleContextHolder.getLocale();
        //System.out.println(locale);
        String userNameLogOut = (String)model.asMap().get("userName");
        if (userNameLogOut != null){
            model.addAttribute("userNameLogOut",userNameLogOut);
        }


        model.addAttribute("error",error != null);
        model.addAttribute("logout",logout != null);
        return "login";
    }

    @RequestMapping(value = "/logoutt",method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) auth.getPrincipal();



        if (auth != null){
            Locale locale = LocaleContextHolder.getLocale();
            if (locale == Locale.ENGLISH){
                System.out.println("english");
            }else{
                System.out.println("ukr");
            }
            //new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        System.out.println(user.getUsername());

        redirectAttributes.addFlashAttribute("username",user.getUsername());


        return "redirect:/login?logout";
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
