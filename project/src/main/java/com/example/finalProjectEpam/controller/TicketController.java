package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping(value = {"/users/getroute","/admin/getroute"})
public class TicketController {

    private PriceListCitiesImpl priceListCitiesImpl;
    private TicketServiceImpl ticketServiceImpl;
    private UserServiceImpl userServiceImpl;

    private Authentication authentication ;

    @Autowired
    public TicketController(UserServiceImpl userServiceImpl,TicketServiceImpl ticketServiceImpl,PriceListCitiesImpl priceListCitiesImpl){
        this.userServiceImpl = userServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
        this.priceListCitiesImpl = priceListCitiesImpl;
    }


    @RequestMapping(value = "/ticket",method = RequestMethod.POST)
    public ModelAndView registration(PriceListCities city,Ticket ticket,Model model,RedirectAttributes redirectAttributes) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();

        ticketServiceImpl.addUserTicket(city,user,ticket);
        redirectAttributes.addFlashAttribute("alertTicket",1);

       if (user.getAuthorities().toArray()[0].toString().equals("ROLE_USER")) {
           return new ModelAndView("redirect:/users/findroute");
       }else {
           return new ModelAndView("redirect:/admin/findroute");
       }
    }


    @RequestMapping("/ticket")
    public ModelAndView  ticket(PriceListCities cities, Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails user = (UsersDetails) authentication.getPrincipal();

        userServiceImpl.getLocale(model);
        ticketServiceImpl.findCity(cities);

        modelAndView.addObject("ticket",cities);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("ticket");

        return modelAndView;

    }



}
