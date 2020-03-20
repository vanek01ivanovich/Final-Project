package com.example.finalProjectEpam.controller;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.implementation.PriceListCitiesImpl;
import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    private UserServiceImpl userServiceImpl;
    private PriceListCitiesImpl priceListCitiesImpl;



    @Autowired
    public UserController(UserServiceImpl userServiceImpl,PriceListCitiesImpl priceListCitiesImpl){
        this.userServiceImpl = userServiceImpl;
        this.priceListCitiesImpl = priceListCitiesImpl;
    }

    @RequestMapping("/findroute")
    public ModelAndView findStation(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityFrom",new PriceListCities());
        modelAndView.setViewName("findroute");


        Locale locale = LocaleContextHolder.getLocale();

        if (locale == Locale.ENGLISH){
            model.addAttribute("type","hidden");
        }else {
            model.addAttribute("type","NotHidden");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/getroute")
    public @ResponseBody ModelAndView findRoute( PriceListCities city,Model model){
        Locale locale = LocaleContextHolder.getLocale();
        List<PriceListCities> cities;
        if (locale == Locale.ENGLISH) {
             cities = priceListCitiesImpl.findCityByStationFromAndTo(city.getStationFrom(), city.getStationTo());
                model.addAttribute("type","hidden");
        }else {
            System.out.println(city.getStationFromUkr());
            cities = priceListCitiesImpl.findCityByStationFromAndTo(city.getStationFromUkr(), city.getStationToUkr());
            model.addAttribute("type","NotHidden");
        }


        /*ModelAndView modelAndView3 = new ModelAndView();
        modelAndView3.addObject("type","hidden");*/


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities",cities);
        modelAndView.setViewName("cities");
        ModelAndView modelAndView1 = new ModelAndView();
        modelAndView1.addObject("ticketForm",new PriceListCities());
        return  modelAndView;

    }

    /*@RequestMapping("/ticket")
    public @ResponseBody
    ResponseEntity<PriceListCities> ticket(PriceListCities cities){
        *//*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket");*//*

        return new ResponseEntity<>(cities, HttpStatus.OK);


    }*/

/*
@RequestMapping(value = "/getroute")
    public @ResponseBody String findRoute(@ModelAttribute("cityFrom") PriceListCities city, Model model){
        List<PriceListCities> cities = priceListCitiesImpl.findCityByStationFromAndTo(city.getStationFrom(),city.getStationTo());
        model.addAttribute("cities",cities);
        return "cities";
    }
*/


    @RequestMapping("/allusers")
    public @ResponseBody List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @RequestMapping(value = "/findusers",method = RequestMethod.GET)
    public @ResponseBody
    User findUser(){
        return userServiceImpl.findUserByUserName("fdefer");
    }

}
