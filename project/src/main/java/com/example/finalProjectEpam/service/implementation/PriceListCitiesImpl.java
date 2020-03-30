package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.repository.PriceListCitiesRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.PriceListCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public List<PriceListCities> findCityByStationFromAndTo(String stationFrom, String stationTo){
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            return priceListCitiesRepository.findCityByStationFromAndStationTo(stationFrom,stationTo);
        }else{
            return priceListCitiesRepository.findCityByStationFromUkrAndStationToUkr(stationFrom,stationTo);
        }
    }



    public List<PriceListCities> findCityByStationFromAndToAndDate(String stationFrom, String stationTo, String date){


        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            return  priceListCitiesRepository.findCityByStationFromAndStationToAndDate(stationFrom,stationTo,date);
        }else{
            return priceListCitiesRepository.findCityByStationFromUkrAndStationToUkrAndDate(stationFrom,stationTo,date);
        }
    }


    public List<PriceListCities> findTicketByStationFromAndStationToAndTrainName(String stationFrom,String stationTo,String trainName){
        return priceListCitiesRepository.findTicketByStationFromAndStationToAndTrainName(stationFrom,stationTo,trainName);
    }

    public PriceListCities getId(Long id){
        return priceListCitiesRepository.findById(id).get();
    }


    @Override
    public List<PriceListCities> findCitiesForUserRequest(String stationFrom, String stationTo, String date,PriceListCities city) {
        List<PriceListCities> cities;
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH) {

            cities = findCityByStationFromAndToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
            city.setStationFromUkr(cities.get(0).getStationFromUkr());
            city.setStationToUkr(cities.get(0).getStationToUkr());

        }else {

            cities = findCityByStationFromAndToAndDate(city.getStationFromUkr(), city.getStationToUkr(),city.getDate());
            city.setStationFrom(cities.get(0).getStationFrom());
            city.setStationTo(cities.get(0).getStationTo());
        }
        return cities;
    }
}
