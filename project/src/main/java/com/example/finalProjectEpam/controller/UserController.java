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

    private TicketService ticketService;

    Locale locale = LocaleContextHolder.getLocale();

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

    Authentication authentication ;
    @RequestMapping("/findroute")
    public ModelAndView findStation(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityFrom",new PriceListCities());
        modelAndView.setViewName("findroute");

        authentication = SecurityContextHolder.getContext().getAuthentication();



        getCurrentLocale(model);
        return modelAndView;
    }




    @RequestMapping(value = "/getroute")
    public  ModelAndView findRoute(@Validated PriceListCities city,
                                   Model model,
                                   @RequestParam(value = "notFound",required = false) String notFound) throws ParseException {


        UsersDetails user  = (UsersDetails) authentication.getPrincipal();

        java.util.Date dateCity = city.getDateU();
        city.setDate(getFormatDate(dateCity));

        Locale locale = LocaleContextHolder.getLocale();
        List<PriceListCities> cities;
        if (locale == Locale.ENGLISH) {

             cities = priceListCitiesImpl.findCityByStationFromAndToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
             city.setStationFromUkr(cities.get(0).getStationFromUkr());
             city.setStationToUkr(cities.get(0).getStationToUkr());

                model.addAttribute("type","hidden");
        }else {
            System.out.println(city.getStationFromUkr());
            cities = priceListCitiesImpl.findCityByStationFromAndToAndDate(city.getStationFromUkr(), city.getStationToUkr(),city.getDate());
            city.setStationFrom(cities.get(0).getStationFrom());
            city.setStationTo(cities.get(0).getStationTo());
            model.addAttribute("type","NotHidden");
        }


        applicationServiceImpl.addApplication(city,user);

        System.out.println("citioes   " + cities.size());

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

            /*ModelAndView modelAndView1 = new ModelAndView();
            modelAndView1.addObject("ticketForm",new PriceListCities());*/

            return  modelAndView;
        }



    }

    public String getFormatDate(java.util.Date date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        DateFormat format1 = new SimpleDateFormat(pattern);
        String mysqlDateString = format.format(date);


        return mysqlDateString;
    }





    @RequestMapping(value = "/lookAllUserTickets")
    public @ResponseBody ModelAndView getAllUserTickets(Model model){
        getCurrentLocale(model);
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

        getCurrentLocale(model);
       /* Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }
*/

        List<User> allUsers = userServiceImpl.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allUsers",allUsers);
        modelAndView.setViewName("all_users");
        return modelAndView;
    }

    @RequestMapping("/alltickets")
    public @ResponseBody ModelAndView getAllTickets(Model model){
        getCurrentLocale(model);

        List<Ticket> allTickets = ticketServiceImpl.getAllTickets();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTickets",allTickets);
        modelAndView.setViewName("all_tickets");
        return modelAndView;
    }

    @RequestMapping("/allapplications")
    public @ResponseBody ModelAndView getAllApplications(Model model){
        getCurrentLocale(model);

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



    private void getCurrentLocale(Model model){
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

    }

}
