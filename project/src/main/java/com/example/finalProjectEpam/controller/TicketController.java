package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@RestController
@RequestMapping(value = "/users/getroute")
public class TicketController {

    private PriceListCitiesImpl priceListCities;
    private TicketServiceImpl ticketServiceImpl;

    @Autowired
    public TicketController(TicketServiceImpl ticketServiceImpl){
        this.ticketServiceImpl = ticketServiceImpl;
    }



    /*@RequestMapping("/ticket")
    public @ResponseBody
    ResponseEntity<PriceListCities>  ticket(PriceListCities cities){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket");

        return new ResponseEntity<>(cities, HttpStatus.OK);


    }*/
    @RequestMapping("/ticket")
    public ModelAndView  ticket(PriceListCities cities, Model model,Ticket ticket){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();

        //model.addAttribute("username",user.getUsername());
        ticketServiceImpl.addUserTicket(cities,user,ticket);

        Locale locale = LocaleContextHolder.getLocale();

        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticket",cities);
        modelAndView.setViewName("ticket");

        //return new ResponseEntity<>(ticket,HttpStatus.OK);
        return modelAndView;

    }

}
