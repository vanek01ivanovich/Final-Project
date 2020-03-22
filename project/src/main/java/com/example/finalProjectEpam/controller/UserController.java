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
    public @ResponseBody ModelAndView findRoute(@Validated PriceListCities city, Model model) throws ParseException {

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


        ModelAndView modelAndView3 = new ModelAndView();
        modelAndView3.addObject("type","hidden");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cities",cities);
        modelAndView.setViewName("cities");
        ModelAndView modelAndView1 = new ModelAndView();
        modelAndView1.addObject("ticketForm",new PriceListCities());
        return  modelAndView;

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
    public @ResponseBody ModelAndView getAllUsers(){
        List<User> allUsers = userServiceImpl.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_users");
        modelAndView.addObject("dscs","Dcs");
        return modelAndView;
    }

    @RequestMapping(value = "/findusers",method = RequestMethod.GET)
    public @ResponseBody
    User findUser(){
        return userServiceImpl.findUserByUserName("fdefer");
    }

}
