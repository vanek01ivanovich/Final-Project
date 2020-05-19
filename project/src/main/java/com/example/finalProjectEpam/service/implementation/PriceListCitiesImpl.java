package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.repository.PriceListCitiesRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.PriceListCitiesService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PriceListCitiesImpl implements PriceListCitiesService {
    @Autowired
    private PriceListCitiesRepository priceListCitiesRepository;

    @Autowired
    public PriceListCitiesImpl(PriceListCitiesRepository priceListCitiesRepository){
        this.priceListCitiesRepository = priceListCitiesRepository;
    }



    public  boolean findCity(Pageable cityPage, PriceListCities city, ModelAndView modelAndView) throws ParseException {

        Page<PriceListCities> page;

        List<PriceListCities> cities;
        city = getFormatDate(city);

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            cities =  priceListCitiesRepository.findCityByStationFromAndStationToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
            page = priceListCitiesRepository.findByStationFromAndStationToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate(),cityPage);

        }else{

            cities = priceListCitiesRepository.findCityByStationFromUkrAndStationToUkrAndDate(city.getStationFromUkr(), city.getStationToUkr(), city.getDate());
            page = priceListCitiesRepository.findCityByStationFromUkrAndStationToUkrAndDate(city.getStationFromUkr(), city.getStationToUkr(), city.getDate(), cityPage);

        }

        if (cities.size() == 0){
            return false;
        }



        if (locale == Locale.ENGLISH){
            city.setStationFromUkr(cities.get(0).getStationFromUkr());
            city.setStationToUkr(cities.get(0).getStationToUkr());
        }else {
            city.setStationFrom(cities.get(0).getStationFrom());
            city.setStationTo(cities.get(0).getStationTo());
        }
        modelAndView.addObject("page", page);

        return true;
    }


    public List<PriceListCities> findTicketByStationFromAndStationToAndTrainName(String stationFrom,String stationTo,String trainName){
        return priceListCitiesRepository.findTicketByStationFromAndStationToAndTrainName(stationFrom,stationTo,trainName);
    }

    public PriceListCities getId(Long id){
        return priceListCitiesRepository.findById(id).get();
    }


    @Override
    public List<PriceListCities> findCitiesForUserRequest(String stationFrom, String stationTo, String date,PriceListCities city) {
        /*List<PriceListCities> cities;
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH) {

            cities = findCityByStationFromAndToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
            city.setStationFromUkr(cities.get(0).getStationFromUkr());
            city.setStationToUkr(cities.get(0).getStationToUkr());

        }else {

            cities = findCityByStationFromAndToAndDate(city.getStationFromUkr(), city.getStationToUkr(),city.getDate());
            city.setStationFrom(cities.get(0).getStationFrom());
            city.setStationTo(cities.get(0).getStationTo());
        }*/
        return null;
    }

    private PriceListCities getFormatDate(PriceListCities city) throws ParseException {

        java.util.Date dateCity = city.getDateU();
        System.out.println(dateCity);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        DateFormat format1 = new SimpleDateFormat(pattern);
        String mysqlDateString = format.format(dateCity);

        city.setDate(mysqlDateString);

        return city;
    }


}
