package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.Application;
import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.ApplicationServiceImpl;
import com.example.finalProjectEpam.service.implementation.PriceListCitiesImpl;
import com.example.finalProjectEpam.service.implementation.TicketServiceImpl;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

import javax.swing.text.Utilities;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = {"/users","/admin"})
public class UserController {


    private UserServiceImpl userServiceImpl;
    private PriceListCitiesImpl priceListCitiesImpl;
    private ApplicationServiceImpl applicationServiceImpl;
    private TicketServiceImpl ticketServiceImpl;

    Locale locale = LocaleContextHolder.getLocale();

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          PriceListCitiesImpl priceListCitiesImpl,
                          ApplicationServiceImpl applicationServiceImpl,
                          TicketServiceImpl ticketServiceImpl){
        this.userServiceImpl = userServiceImpl;
        this.priceListCitiesImpl = priceListCitiesImpl;
        this.applicationServiceImpl = applicationServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
    }

    Authentication authentication;
    @RequestMapping("/findroute")
    public ModelAndView findStation(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityFrom",new PriceListCities());
        modelAndView.setViewName("findroute");

        authentication = SecurityContextHolder.getContext().getAuthentication();



        Locale locale = LocaleContextHolder.getLocale();

        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

        return modelAndView;
    }


    /*@RequestMapping(value = "/getroute")
    public String route(@Valid PriceListCities city , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(city.getDate());
            return "to same page to shows error fields";
        }
        System.out.println(city.getDate());
        return "sdafc";
    }*/

    @RequestMapping(value = "/getroute")
    public  ModelAndView findRoute(@Validated PriceListCities city,
                                   Model model,
                                   @RequestParam(value = "notFound",required = false) String notFound) throws ParseException {





        UsersDetails user  = (UsersDetails) authentication.getPrincipal();
        //System.out.println(user.getFirstName());
        applicationServiceImpl.addApplication(city,user);




        java.util.Date dateCity = city.getDateU();
        System.out.println(dateCity);
        //java.sql.Date mysqlDateString = getFormatDate(dateCity);
       // System.out.println(mysqlDateString);
        city.setDate(getFormatDate(dateCity));




        Locale locale = LocaleContextHolder.getLocale();
        List<PriceListCities> cities;
        if (locale == Locale.ENGLISH) {
             //cities = priceListCitiesImpl.findCityByStationFromAndTo(city.getStationFrom(), city.getStationTo());
             cities = priceListCitiesImpl.findCityByStationFromAndToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
           // cities = priceListCitiesImpl.findCityByDate(mysqlDateString);
                model.addAttribute("type","hidden");
        }else {
            System.out.println(city.getStationFromUkr());
            cities = priceListCitiesImpl.findCityByStationFromAndToAndDate(city.getStationFromUkr(), city.getStationToUkr(),city.getDate());
            model.addAttribute("type","NotHidden");
        }

        System.out.println(cities);
        System.out.println(cities.size());
        ModelAndView modelAndView = new ModelAndView();
        if (cities.size() == 0){
            model.addAttribute("notFound", true);
            modelAndView.setViewName("findroute");
            return modelAndView;
        }else{
            ModelAndView modelAndView3 = new ModelAndView();
            modelAndView3.addObject("type","hidden");



            modelAndView.addObject("cities",cities);
            modelAndView.setViewName("cities");

            ModelAndView modelAndView1 = new ModelAndView();
            modelAndView1.addObject("ticketForm",new PriceListCities());

            return  modelAndView;
        }



    }

    public String getFormatDate(java.util.Date date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        DateFormat format1 = new SimpleDateFormat(pattern);
        String mysqlDateString = format.format(date);

       /* java.util.Date mysqlDate =  format1.parse(mysqlDateString);
        java.sql.Date date1 = new java.sql.Date(mysqlDate.getTime());*/
        //System.out.println(date1);
        return mysqlDateString;
    }








    @RequestMapping("/allusers")
    public @ResponseBody ModelAndView getAllUsers(Model model){


        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }


        List<User> allUsers = userServiceImpl.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allUsers",allUsers);
        modelAndView.setViewName("all_users");
        return modelAndView;
    }

    @RequestMapping("/alltickets")
    public @ResponseBody ModelAndView getAllTickets(Model model){
        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

        List<Ticket> allTickets = ticketServiceImpl.getAllTickets();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTickets",allTickets);
        modelAndView.setViewName("all_tickets");
        return modelAndView;
    }

    @RequestMapping("/allapplications")
    public @ResponseBody ModelAndView getAllApplications(Model model){
        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

        List<Application> allTickets = applicationServiceImpl.getAllApplications();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allApplications",allTickets);
        modelAndView.setViewName("all_applications");
        return modelAndView;
    }


    @RequestMapping(value = "/findusers",method = RequestMethod.GET)
    public @ResponseBody
    User findUser(){
        return userServiceImpl.findUserByUserName("fdefer");
    }

}
