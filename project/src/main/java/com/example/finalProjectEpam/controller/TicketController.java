package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.PriceListCitiesImpl;
import com.example.finalProjectEpam.service.implementation.TicketServiceImpl;
import com.example.finalProjectEpam.service.serviceInterfaces.TicketService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = {"/users/getroute","/admin/getroute"})
public class TicketController {

    private PriceListCitiesImpl priceListCitiesImpl;
    private TicketServiceImpl ticketServiceImpl;


    @Autowired
    public TicketController(TicketServiceImpl ticketServiceImpl,PriceListCitiesImpl priceListCitiesImpl){
        this.ticketServiceImpl = ticketServiceImpl;
        this.priceListCitiesImpl = priceListCitiesImpl;
    }


    @RequestMapping(value = "/ticket",method = RequestMethod.POST)
    public ModelAndView registration(PriceListCities cities,Ticket ticket,Model model,RedirectAttributes redirectAttributes){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();
        Locale locale = LocaleContextHolder.getLocale();
        List<PriceListCities> citiesTicket;
        if (locale == Locale.ENGLISH){
            citiesTicket = priceListCitiesImpl.findCityByStationFromAndToAndDate(cities.getStationFrom(), cities.getStationTo(),cities.getDate());
            cities.setStationFromUkr(citiesTicket.get(0).getStationFromUkr());
            cities.setStationToUkr(citiesTicket.get(0).getStationToUkr());
            model.addAttribute("type","hidden");
        }else {
            citiesTicket = priceListCitiesImpl.findCityByStationFromAndToAndDate(cities.getStationFromUkr(), cities.getStationToUkr(),cities.getDate());
            cities.setStationFrom(citiesTicket.get(0).getStationFrom());
            cities.setStationTo(citiesTicket.get(0).getStationTo());
            model.addAttribute("type","NotHidden");
        }


        ticketServiceImpl.addUserTicket(cities,user,ticket);
        Integer al = 1;
        redirectAttributes.addFlashAttribute("alertTicket",al);

       if (user.getAuthorities().toArray()[0].toString().equals("ROLE_USER")) {
           return new ModelAndView("redirect:/users/findroute");
       }else {
           return new ModelAndView("redirect:/admin/findroute");
       }
    }


    @RequestMapping("/ticket")
    public ModelAndView  ticket( PriceListCities cities, Model model, Ticket ticket, RedirectAttributes redirectAttrs){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();



        Locale locale = LocaleContextHolder.getLocale();

        List<PriceListCities> citiesTicket;
        if (locale == Locale.ENGLISH){

            model.addAttribute("type","hidden");
        }else {

            model.addAttribute("type","NotHidden");
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticket",cities);
        modelAndView.addObject("user",user);


        model.addAttribute("user",user);
        model.addAttribute("ticket",cities);


        modelAndView.setViewName("ticket");



        return modelAndView;

    }



}
