package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.Application;
import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.ApplicationServiceImpl;
import com.example.finalProjectEpam.service.implementation.PriceListCitiesImpl;
import com.example.finalProjectEpam.service.implementation.TicketServiceImpl;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import com.example.finalProjectEpam.service.serviceInterfaces.TicketService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Utilities;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/users","/admin"})
public class UserController {


    private UserServiceImpl userServiceImpl;
    private PriceListCitiesImpl priceListCitiesImpl;
    private ApplicationServiceImpl applicationServiceImpl;
    private TicketServiceImpl ticketServiceImpl;

    private TicketService ticketService;

    private Authentication authentication;



    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          PriceListCitiesImpl priceListCitiesImpl,
                          ApplicationServiceImpl applicationServiceImpl,
                          TicketServiceImpl ticketServiceImpl,TicketService ticketService){
        this.userServiceImpl = userServiceImpl;
        this.priceListCitiesImpl = priceListCitiesImpl;
        this.applicationServiceImpl = applicationServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
        this.ticketService = ticketService;
    }
    @RequestMapping("/findroute")
    public ModelAndView findStation(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityFrom",new PriceListCities());
        modelAndView.setViewName("findroute");

        authentication = SecurityContextHolder.getContext().getAuthentication();

        userServiceImpl.getLocale(model);
        return modelAndView;
    }




    @RequestMapping(value = "/getroute")
    public  ModelAndView findRoute(@PageableDefault(size = 3) Pageable cityPage,
                                   PriceListCities city,
                                   Model model,
                                   @RequestParam(value = "notFound",required = false) String notFound,
                                   HttpServletRequest request, RedirectAttributes redirectAttrs) throws ParseException, UnsupportedEncodingException {

        //RedirectView redirectView = new RedirectView();
        ModelAndView modelAndView = new ModelAndView();
        UsersDetails user  = (UsersDetails) authentication.getPrincipal();
        userServiceImpl.getLocale(model);

        //String url= request.getRequestURL().toString() + "?" + request.getQueryString();
        //System.out.println(URLDecoder.decode(url,"UTF-8"));



        if (priceListCitiesImpl.findCity(cityPage,city,modelAndView)){
            applicationServiceImpl.addApplication(city,user);
            /*Locale locale = LocaleContextHolder.getLocale();
            if (locale != Locale.ENGLISH){
                city.setStationFromUkr(URLDecoder.decode(city.getStationFromUkr(),"UTF-8"));
                System.out.println(city.getStationFromUkr());
            }*/
            //redirectAttrs.addFlashAttribute(URLDecoder.decode(url,"UTF-8"));

            modelAndView.setViewName("cities");
        }else{
            model.addAttribute("notFound", true);
            //redirectAttrs.addFlashAttribute(URLEncoder.encode(url,"UTF-8"));
            modelAndView.setViewName("findroute");
        }

        //redirectAttrs.addFlashAttribute(URLEncoder.encode(url,"UTF-8"));
       // modelAndView.setViewName("redirect:" + URLDecoder.decode(url,"UTF-8"));

        //redirectView.setUrl(URLDecoder.decode(url,"windows-1251"));
        return  modelAndView;


    }



    @RequestMapping(value = "/lookAllUserTickets")
    public @ResponseBody ModelAndView getAllUserTickets(Model model){

        userServiceImpl.getLocale(model);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user  = (UsersDetails) authentication.getPrincipal();
        System.out.println(user.getUsername());
        List<Ticket> tickets = ticketService.findAllTicketsByUserName(user.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTicketsForUser",tickets);
        modelAndView.setViewName("all_tickets_for_user");
        return modelAndView;

    }

    @RequestMapping(value = "/allusers")
    public @ResponseBody ModelAndView getAllUsers(Model model){


        userServiceImpl.getLocale(model);
        List<User> allUsers = userServiceImpl.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allUsers",allUsers);
        modelAndView.setViewName("all_users");
        return modelAndView;
    }

    @RequestMapping("/alltickets")
    public @ResponseBody ModelAndView getAllTickets(Model model){

        userServiceImpl.getLocale(model);
        List<Ticket> allTickets = ticketServiceImpl.getAllTickets();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTickets",allTickets);
        modelAndView.setViewName("all_tickets");
        return modelAndView;
    }

    @RequestMapping("/allapplications")
    public @ResponseBody ModelAndView getAllApplications(Model model){
        userServiceImpl.getLocale(model);

        List<Application> allApplications = applicationServiceImpl.getAllApplications();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(allApplications);
        modelAndView.addObject("allApplications",allApplications);
        modelAndView.setViewName("all_applications");
        return modelAndView;
    }


    @RequestMapping(value = "/findusers",method = RequestMethod.GET)
    public @ResponseBody
    User findUser(){
        return userServiceImpl.findUserByUserName("fdefer");
    }





}
